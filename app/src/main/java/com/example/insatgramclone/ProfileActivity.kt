package com.example.insatgramclone
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.insatgramclone.databinding.ActivityProfileBinding
import com.example.insatgramclone.databinding.ActivityLoginAcivityBinding
import com.google.android.material.tabs.TabLayout.TabGravity
import com.google.firebase.auth.FirebaseAuth

private const val  TAG ="ProfileActivity"
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_profile)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_logout) {
            Log.i(TAG, "user wants to Logout ")
            FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,SignInActivity::class.java ))
        }
        return super.onOptionsItemSelected(item)
    }
}