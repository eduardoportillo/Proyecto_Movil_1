package com.moviles.marketplace.ui.activities.auth

import android.content.ClipData.newIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.R
import com.moviles.marketplace.api.UserRepository
import com.moviles.marketplace.databinding.ActivityLoginBinding
import com.moviles.marketplace.models.User
import com.moviles.marketplace.ui.activities.StartUI

class LoginActivity : AppCompatActivity(), UserRepository.LoginUserListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkUserLogin()
        configButtons()
    }

    fun checkUserLogin() {
        if (sharedPref.getToken().isNotEmpty()) {
            startActivity(Intent(this, StartUI::class.java))
            finish()
        }
    }

    fun configButtons() {
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        if (binding.inputEmailLogin.text.isEmpty()) {
            binding.inputEmailLogin.error = "El Email es necesario"
        }else if(binding.inputPasswordLogin.text.isEmpty()){
            binding.inputPasswordLogin.error = "La contraseña es necesaria"
        }else {
            val userData = User(
                email = binding.inputEmailLogin.text.toString(),
                password = binding.inputPasswordLogin.text.toString(),
                notification_id = sharedPref.getNotificationId(),
            )
            UserRepository().loginUser(userData, this)
        }
    }

    override fun LoginUserReady(user: User) {
            sharedPref.setToken(user.access_token)
            startActivity(Intent(this, StartUI::class.java))
            finish()
    }

    override fun onUserLoginError(t: Throwable) {
        binding.errorLabel.text = t.message
        Log.d("error_response_api", t.toString())
    }
}
