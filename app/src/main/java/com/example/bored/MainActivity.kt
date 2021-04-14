package com.example.bored

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bored.databinding.ActivityMainBinding
import com.example.bored.utils.Result
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

        observeLiveData()
    }

    private fun observeLiveData() {
        viewmodel.uiState.observe(this) {
            when(it) {
                is Result.Idle -> {
                    Log.d("MainActivity", "Idle")

                }//stop loading
                is Result.Error -> {
                    Toast.makeText(this, it.errorMsg, Toast.LENGTH_SHORT).show()
                }
                is Result.Loading -> {
                    Log.d("MainActivity", "Loading")

                }//show loading
            }
        }

        viewmodel.activity.observe(this) {
            //show the response in the area
            Log.d("MainActivity", "The activity is : $it")
        }
    }


}