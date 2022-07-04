package com.moviles.marketplace.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.moviles.marketplace.MarketPlaceApplication
import com.moviles.marketplace.R
import com.moviles.marketplace.databinding.ActivityMainBinding
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

        MarketPlaceApplication.sharedPref.setToken("52|DeXTcChmhXz2votQKqn4L9EIHZhwcim50BMvy2DU")

        setupFirebaseToken()
    }
}