package com.jfalstaff.qrity.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jfalstaff.qrity.R
import com.jfalstaff.qrity.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, StartFragment.newInstance())
                .commit()
        }
    }
}