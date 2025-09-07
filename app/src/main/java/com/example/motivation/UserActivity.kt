package com.example.motivation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG: String = "UserActivity"
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListeners()
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            handleClick(it)
        }
    }

    private fun handleClick(view: View) {
        when (view.id) {
            binding.buttonSave.id -> {
                Log.i(TAG, "Save button clicked")
                saveUser()
            }
        }
    }

    private fun saveUser() {}
}