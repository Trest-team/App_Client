package com.example.pigo_app_client.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pigo_app_client.R
import com.example.pigo_app_client.databinding.ActivityMainBinding
import com.example.pigo_app_client.ui.main.fragment.CounselFragment
import com.example.pigo_app_client.ui.main.fragment.HomeFragment
import com.example.pigo_app_client.ui.main.fragment.MyPageFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initDBinding()

        initBottomNav()

    }

    private fun initDBinding() {
        with(binding){
            lifecycleOwner = this@MainActivity
            activity = this@MainActivity
            vm = viewModel
        }
    }

    private fun initBottomNav() {
        //supportFragmentManager.beginTransaction().replace(R.id.fragment_container_mainActivity, HomeFragment()).commit()

        binding.bottomNavigationViewMainActivity.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.action_Home -> {
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.action_Counsel -> {
                    replaceFragment(CounselFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.action_MyPage -> {
                    replaceFragment(MyPageFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        var fragmentTransaction = supportFragmentManager.beginTransaction()


        fragmentTransaction.replace(R.id.fragment_container_mainActivity, fragment)
        fragmentTransaction.commit()
    }



}