package com.example.pigo_app_client.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.pigo_app_client.MainActivity
import com.example.pigo_app_client.R
import com.example.pigo_app_client.databinding.ActivityLoginBinding
import com.example.pigo_app_client.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.activity = this
        binding.lifecycleOwner = this

    }

    fun login(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun signUp(){
        startActivity(Intent(this, SignUpActivity::class.java))
    }

}