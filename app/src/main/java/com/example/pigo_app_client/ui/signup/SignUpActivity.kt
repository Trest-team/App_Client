package com.example.pigo_app_client.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.pigo_app_client.R
import com.example.pigo_app_client.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        val adapter = MAdapter(binding, viewModel)

        with(binding){
            lifecycleOwner = this@SignUpActivity
            activity = this@SignUpActivity
            this.viewModel = viewModel
            viewPagerSignUpActivity.adapter = adapter
            viewPagerSignUpActivity.isUserInputEnabled = false

            adapter.notifyDataSetChanged()
        }

    }

    fun next() {
        Toast.makeText(baseContext, "계속하기", Toast.LENGTH_SHORT).show()
        Log.d("TestLog", "계속하기")
        binding.viewPagerSignUpActivity.currentItem+= 1
    }

    fun signUp(){
        Toast.makeText(baseContext, "회원가입 완료!", Toast.LENGTH_SHORT).show()
        finish()
    }


}