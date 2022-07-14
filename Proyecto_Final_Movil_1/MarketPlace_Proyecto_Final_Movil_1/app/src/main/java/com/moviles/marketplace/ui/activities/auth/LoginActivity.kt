package com.moviles.marketplace.ui.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import com.moviles.marketplace.BottomNavigationActivity
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.R
import com.moviles.marketplace.api.UserRepository
import com.moviles.marketplace.databinding.ActivityLoginBinding
import com.moviles.marketplace.models.User

class LoginActivity : AppCompatActivity(), UserRepository.LoginUserListener,
    UserRepository.GetUserListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFirebaseToken()
        checkUserLogin()
        configButtons()
    }

    fun checkUserLogin() {
        if (sharedPref.getToken().isNotEmpty()) {
            startActivity(Intent(this, BottomNavigationActivity::class.java))
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
            UserRepository().getUser(this)
            startActivity(Intent(this, BottomNavigationActivity::class.java))
            finish()
    }

    override fun onUserLoginError(t: Throwable) {
        binding.errorLabel.text = t.message
        Log.d("error_response_api", t.toString())
    }

    override fun getUserReady(user: User) {
        sharedPref.setUserInfo(user)
    }

    override fun onUserGetError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }
    private fun setupFirebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TOKEN_FIREBASE", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            sharedPref.setNotificationId(token!!)

        }
    }

}
