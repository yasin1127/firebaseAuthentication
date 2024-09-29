package com.example.firebaseauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.databinding.ActivityRegisterBinding
import com.example.firebaseauthentication.viewmodel.AuthViewModels

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModels
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModels::class.java)

        binding.regbtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passEt.text.toString()
            val conPass = binding.rePass.text.toString()


            if (email.isEmpty() || pass.isEmpty() || conPass.isEmpty()) {
                Toast.makeText(this@RegisterActivity, "Please Fill All Fields", Toast.LENGTH_SHORT)
                    .show()
            } else if (!pass.equals(conPass)) {
                Toast.makeText(this@RegisterActivity, "Password Not Matched", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.signUp(email, conPass).observe(this, Observer { result ->
                    Toast.makeText(this@RegisterActivity, result, Toast.LENGTH_SHORT).show()


                    if (result == "Signup Success") {
                        startActivity(Intent(this@RegisterActivity,HomeActivity::class.java))
                    }
                })
            }
        }
        binding.alreadyhaveaccount.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }


    }
}