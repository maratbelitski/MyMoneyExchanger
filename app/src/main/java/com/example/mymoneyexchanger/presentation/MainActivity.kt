package com.example.mymoneyexchanger.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mymoneyexchanger.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //aur Activity is just a container
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}