package com.example.firebaseauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.databinding.ActivityLoginBinding
import com.example.firebaseauthentication.databinding.ActivityRegisterBinding
import com.example.firebaseauthentication.viewmodel.AuthViewModels
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModels
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel= ViewModelProvider(this).get(AuthViewModels::class.java)

        binding.loginbtn.setOnClickListener {
            val email=binding.emailEt.text.toString()
            val pass=binding.passEt.text.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Please Fill All Fields", Toast.LENGTH_SHORT)
                    .show()

            } else {
                viewModel.signIn(email, pass).observe(this, Observer { result ->
                    Toast.makeText(this@LoginActivity, result, Toast.LENGTH_SHORT).show()


                    if (result == "SignIn Success") {
                        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                    }
                })
            }
        }
        binding.donthaveaccount.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser?.uid!=null){
            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
        }
    }

    }
