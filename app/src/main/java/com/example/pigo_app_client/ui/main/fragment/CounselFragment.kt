package com.example.pigo_app_client.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.pigo_app_client.R
import com.example.pigo_app_client.databinding.FragmentCounselBinding
import com.example.pigo_app_client.ui.chat.ChattingActivity


class CounselFragment : Fragment() {

    lateinit var v : View
    lateinit var binding: FragmentCounselBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_counsel, container, false)
        with(binding){
            fragment = this@CounselFragment
            lifecycleOwner = this@CounselFragment

        }



        v = binding.root




        return v

    }

    fun startChattingActivity(){
        startActivity(Intent(v.context, ChattingActivity::class.java))
    }

}