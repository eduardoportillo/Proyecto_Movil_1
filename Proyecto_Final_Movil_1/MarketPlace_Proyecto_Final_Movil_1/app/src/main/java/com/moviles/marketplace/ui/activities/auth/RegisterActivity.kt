package com.moviles.marketplace.ui.activities.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moviles.marketplace.R
import com.moviles.marketplace.databinding.ActivityLoginBinding
import com.moviles.marketplace.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}