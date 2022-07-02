package com.moviles.marketplace

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.moviles.marketplace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setupFirebaseToken()
    }

    private fun setupFirebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener{ task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Error al obtener el token de registro de FCM", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d("TOKEN_FIREBASE", token)
//            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        }
    }
}