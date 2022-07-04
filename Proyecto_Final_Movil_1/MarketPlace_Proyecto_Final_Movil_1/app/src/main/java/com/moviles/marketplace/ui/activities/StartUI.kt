package com.moviles.marketplace.ui.activities

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.moviles.marketplace.MarketPlaceApplication
import com.moviles.marketplace.R
import com.moviles.marketplace.databinding.ActivityStartUiActivityBinding

class StartUI : AppCompatActivity() {
    private lateinit var binding: ActivityStartUiActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartUiActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setupFirebaseToken()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupFirebaseToken() { // TODO setupFirebaseToken() se tiene que poner en el Login
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(
                    ContentValues.TAG,
                    "Error al obtener el token de registro de FCM",
                    task.exception
                )
                return@addOnCompleteListener
            }

            val token = task.result

            Log.d("TOKEN_FIREBASE", token)
        }
    }
}