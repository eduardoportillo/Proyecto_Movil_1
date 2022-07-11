package com.moviles.marketplace.ui.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.moviles.marketplace.BottomNavigationActivity
import com.moviles.marketplace.MarketPlaceApplication
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.R
import com.moviles.marketplace.api.UserRepository
import com.moviles.marketplace.databinding.ActivityLoginBinding
import com.moviles.marketplace.databinding.ActivityRegisterBinding
import com.moviles.marketplace.models.User

class RegisterActivity : AppCompatActivity(), UserRepository.CreateUserListener{
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configButton()
    }

    fun configButton() {
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnRegister.setOnClickListener {
            if (binding.nameRegisterInput.text.isEmpty()) {
                binding.nameRegisterInput.error = "El Email es necesario"
            }else if(binding.emailRegisterInput.text.isEmpty()){
                binding.emailRegisterInput.error = "La contraseña es necesaria"
            }else if(binding.passwordRegisterInput.text.isEmpty()){
                binding.passwordRegisterInput.error = "La contraseña es necesaria"
            }else {
                val userData = User(
                    name = binding.nameRegisterInput.text.toString(),
                    email =  binding.emailRegisterInput.text.toString(),
                    password = binding.passwordRegisterInput.text.toString(),
                    notification_id = sharedPref.getNotificationId(),
                )
                UserRepository().createUser(userData, this)
            }
        }
    }

    override fun RegisterUserReady(user: User) {
        val toast = Toast.makeText(
            applicationContext,
            "Usuario Creado", Toast.LENGTH_SHORT
        )
        toast.show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onUserRegisterError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }
}