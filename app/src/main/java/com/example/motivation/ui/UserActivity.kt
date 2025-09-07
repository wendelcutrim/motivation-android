package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.MotivationConstants
import com.example.motivation.R
import com.example.motivation.databinding.ActivityUserBinding
import com.example.motivation.helper.SecurityPreferences

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG: String = "UserActivity"
    private lateinit var binding: ActivityUserBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        securityPreferences = SecurityPreferences(this)
        enableEdgeToEdge()

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
        * Irá verificar se existe um nome de usuário salvo no SharedPreferences, caso tiver, irá redirecionar para a acitivity de frases.
         */
//        hasName()
        setListeners();
    }

    private fun setListeners(): Unit {
        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(v: View?): Unit {
        v?.let {
            handleClick(it)
        }
    }

    private fun handleClick(view: View): Unit {
        when (view.id) {
            binding.buttonSave.id -> {
                Log.i(TAG, "Save button clicked")
                saveUser()
            }
        }
    }

    private fun saveUser(): Unit {
        showSpinner(true);

        val name = binding.edittextName.text.toString();
        val isValid = validateName(name);
        val toastDuration = Toast.LENGTH_SHORT;
        val errorMessage = "Nome é obrigatório!";

        if (!isValid) {
            val toast = Toast.makeText(this, errorMessage, toastDuration);
            showSpinner(false);
            return toast.show();
        }

        securityPreferences.storeString(MotivationConstants.USER_NAME, name);
        redirectTo(PhraseActivity::class.java);
    }

    private fun validateName(name: String): Boolean {
        val isNotEmpty = name.isNotEmpty();

        if (!isNotEmpty) {
            binding.edittextName.error = "Nome é obrigatório!";
            return false;
        }

        if (isNotEmpty && name.length < 3) {
            binding.edittextName.error = "Nome deve ter no mínimo 3 caracteres!";
            return false
        }

        binding.edittextName.error = null;
        return true;
    }

    private fun <T: AppCompatActivity> redirectTo(activityClass: Class<T>, bundle: Bundle? = null): Unit {
        val intent: Intent = Intent(this, activityClass);

        bundle?.let {
            intent.putExtras(it)
            Log.i(TAG, "Redirecting to ${activityClass.simpleName} with bundle.")
        } ?: run {
            Log.i(TAG, "Redirecting to ${activityClass.simpleName} without bundle.")
        }

        startActivity(intent)
        finish()
    }

    private fun showSpinner(show: Boolean): Unit {
        if (show) {
            binding.buttonSave.visibility = View.GONE;
            binding.progressbarLoading.visibility = View.VISIBLE;
            binding.edittextName.visibility = View.GONE;
            binding.buttonSave.visibility = View.GONE;
        } else {
            binding.buttonSave.visibility = View.VISIBLE;
            binding.progressbarLoading.visibility = View.GONE;
            binding.edittextName.visibility = View.VISIBLE;
            binding.buttonSave.visibility = View.VISIBLE;
        }
    }
    
    private fun hasName(): Unit {
        showSpinner(true);
        val name = securityPreferences.getString(MotivationConstants.USER_NAME);
        
        if (name.isNotEmpty()) {
            redirectTo(PhraseActivity::class.java)
        } else {
            showSpinner(false)
        }
    }
}