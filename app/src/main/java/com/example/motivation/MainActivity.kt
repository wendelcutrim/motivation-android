package com.example.motivation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.databinding.ActivityMainBinding

private var TAG: String = "MainActivity"

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imagebuttonRandom.setOnClickListener(this);
        binding.imagebuttonHappy.setOnClickListener(this);
        binding.imagebuttonLight.setOnClickListener(this);
        binding.buttonNewText.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        v?.let {
            handleClick(it)
        }
    }

    private fun handleClick(v: View) {
        when (v.id) {
            binding.imagebuttonRandom.id -> {
                Log.i(TAG, "Random button clicked")
            }

            binding.imagebuttonHappy.id -> {
                Log.i(TAG, "Happy button clicked")
            }

            binding.imagebuttonLight.id -> {
                Log.i(TAG, "Light button clicked")
            }

            binding.buttonNewText.id -> {
                Log.i(TAG, "New text button clicked")
            }
        }
    }
}