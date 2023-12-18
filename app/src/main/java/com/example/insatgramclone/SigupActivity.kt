package com.example.insatgramclone
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.insatgramclone.databinding.ActivitySigupBinding
import com.google.firebase.auth.FirebaseAuth
import android.os.Bundle
import android.util.Log

class SigupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        }else {
                            val exception = task.exception
                            Toast.makeText(this, exception?.message, Toast.LENGTH_SHORT).show()
                            Log.e("Firebase", "Error creating user: ${exception?.message}")
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}