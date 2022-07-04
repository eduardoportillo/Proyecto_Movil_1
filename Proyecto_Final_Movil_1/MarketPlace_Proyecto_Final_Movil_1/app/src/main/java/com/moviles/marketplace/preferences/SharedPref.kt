package com.moviles.marketplace.preferences

import android.content.Context

class SharedPref(val context: Context) {
    val SHARED_NAME = "SharedDB"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun setToken(token: String) {
        storage.edit().putString("token", token).apply()
    }

    fun getToken(): String {
        return storage.getString("token", "")!!
    }

    fun setNotificationId(notificationId: String) {
        storage.edit().putString("notificationId", notificationId).apply()
    }

    fun getNotificationId(): String? {
        return storage.getString("notificationId", null)
    }
}