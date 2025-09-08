package com.example.motivation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.MotivationConstants
import com.example.motivation.R
import com.example.motivation.databinding.ActivityPhraseBinding
import com.example.motivation.helper.SecurityPreferences

class PhraseActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG: String = "PhraseActivity"
    private lateinit var binding: ActivityPhraseBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        securityPreferences = SecurityPreferences(this)
        enableEdgeToEdge()

        binding = ActivityPhraseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getUserName()
        setListeners()
    }

    override fun onClick(v: View?) {
        v?.let {
            handleClick(it)
        }
    }

    private fun setListeners() {
        binding.imagebuttonRandom.setOnClickListener(this);
        binding.imagebuttonHappy.setOnClickListener(this);
        binding.imagebuttonLight.setOnClickListener(this);
        binding.buttonNewText.setOnClickListener(this);
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
                handleNewPhrase();
            }
        }
    }

    private fun handleNewPhrase() {
        binding.buttonNewText.text = "Gerou nova frase"
    }

    private fun getUserName() {
        val name = securityPreferences.getString(MotivationConstants.USER_NAME);

        if (name.isNotEmpty()) {
            binding.textviewRegards.text = "Olá, $name!";
            binding.textviewRegards.visibility = View.VISIBLE;
            return;
        }

        binding.textviewRegards.visibility = View.GONE;
    }
}