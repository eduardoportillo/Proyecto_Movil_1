package com.moviles.marketplace

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.moviles.marketplace.databinding.ActivityBottomNavigationBinding;

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_marketplace,
                R.id.navigation_product_user,
                R.id.navigation_chat,
                R.id.navigation_user_info
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        checkPermissions()
    }

    private fun checkPermissions() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
//            PackageManager.PERMISSION_GRANTED
//        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1)
//        }else if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
//            PackageManager.PERMISSION_GRANTED
//        ){
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
//        }else if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
//            PackageManager.PERMISSION_GRANTED
//        ){
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
//        }else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
//            PackageManager.PERMISSION_GRANTED
//        ){
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
//        }else if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
//            PackageManager.PERMISSION_GRANTED
//        ){
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
//        }else if (ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE) !=
//            PackageManager.PERMISSION_GRANTED
//        ){
            requestPermissions(arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE), 1)
//        }
    }
}