package com.example.firebaseauthentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.databinding.ActivityHomeBinding
import com.example.firebaseauthentication.databinding.ActivityRegisterBinding
import com.example.firebaseauthentication.viewmodel.AuthViewModels

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModels
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel= ViewModelProvider(this).get(AuthViewModels::class.java)

        binding.logoutbtn.setOnClickListener {
            viewModel.signOut()
            startActivity(Intent(this@HomeActivity,LoginActivity::class.java))
        }


    }
}