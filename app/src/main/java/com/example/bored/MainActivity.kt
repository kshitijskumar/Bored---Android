package com.example.bored

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bored.databinding.ActivityMainBinding
import com.example.bored.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewmodel by lazy {
        MainViewModel.providesMainViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}