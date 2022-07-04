package com.moviles.marketplace

import android.app.Application
import com.moviles.marketplace.preferences.SharedPref

class MarketPlaceApplication: Application() {
    companion object {
        lateinit var sharedPref: SharedPref
    }

    override fun onCreate() {
        super.onCreate()
        sharedPref = SharedPref(applicationContext)
    }
}
