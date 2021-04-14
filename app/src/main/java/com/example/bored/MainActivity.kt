package com.example.bored

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

        setupViews()
        observeLiveData()
    }

    private fun setupViews() {
        binding.btnGetRandom.setOnClickListener {
            viewmodel.getRandomActivity()
        }
    }

    private fun observeLiveData() {
        viewmodel.uiState.observe(this) {
            when(it) {
                is Result.Idle -> {
                    binding.progressBar.visibility = View.GONE
                }
                is Result.Error -> {
                    Toast.makeText(this, it.errorMsg, Toast.LENGTH_SHORT).show()
                }
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        viewmodel.activity.observe(this) {
            //show the response in the area
            binding.apply {
                tvActivity.text = it.activity
                tvAccess.text = it.accessibility.toString()
                tvType.text = it.type
                tvPrice.text = it.price.toString()
            }
        }
    }


}