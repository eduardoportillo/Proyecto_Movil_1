package com.moviles.marketplace.ui.activities.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.R
import com.moviles.marketplace.databinding.ActivityLoginBinding
import com.moviles.marketplace.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun checkUserLogin() {
        if (sharedPref.getToken().isNotEmpty()) {

            finish()
        }
    }


}
