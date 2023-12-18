package com.example.insatgramclone

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.insatgramclone.databinding.ItemPostBinding
import com.example.insatgramclone.models.Post
import com.example.insatgramclone.models.User
import java.math.BigInteger
import java.security.MessageDigest

class PostsAdapter(val context: Context, val posts: List<Post>) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Use View Binding to inflate the layout
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }


    inner class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            val username = post.user?.username ?: ""
            binding.tvUsername.text = username
            binding.tvDescription.text = post.description
            Glide.with(context).load(post.imageUrl).into(binding.ivPost)
            Glide.with(context).load(getProfileImageUrl(username)).into(binding.ivProfileImage)
            binding.tvRelativeTime.text = DateUtils.getRelativeTimeSpanString(post.creationTimeMs)
        }

        private fun getProfileImageUrl(username: String): String {
            val digest = MessageDigest.getInstance("MD5")
            val hash = digest.digest(username.toByteArray())
            val bigInt = BigInteger(hash)
            val hex = bigInt.abs().toString(16)
            return "https://www.gravatar.com/avatar/$hex?d=identicon"
        }

    }
}
