package com.example.homefinder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homefinder.adapters.ViewPagerAdapter
import com.example.homefinder.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabView,binding.viewPager2){tab,position ->
           when(position){
               0->{
                   tab.text = "Login"
               }
               1 -> {
                   tab.text = "SignUp"
               }
           }
        }.attach()

    }
}