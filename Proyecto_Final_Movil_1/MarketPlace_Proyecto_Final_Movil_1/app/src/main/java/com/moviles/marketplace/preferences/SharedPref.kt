package com.moviles.marketplace.preferences

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import com.moviles.marketplace.models.User

class SharedPref(val context: Context) {
    val SHARED_NAME = "SharedDB"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun setUserInfo(user: User) {
        storage.edit().putInt("id", user.id!!).apply()
    }

    fun getUserId(): Int {
        return storage.getInt("id", -1)
    }

    fun setToken(token: String?) {
        storage.edit().putString("token", token).apply()
    }

    fun getToken(): String {
        return storage.getString("token", "")!!
    }

    fun setNotificationId(notificationId: String?) {
        storage.edit().putString("notificationId", notificationId).apply()
    }

    fun getNotificationId(): String? {
        return storage.getString("notificationId", null)
    }

    fun setLatitude(latitude: String) {
        storage.edit().putString("latitude", latitude).apply()
    }

    fun getLatitude(): String {
        return storage.getString("latitude", "-17.783092602183373")!!
    }

    fun setLongitude(longitude: String) {
        storage.edit().putString("longitude", longitude).apply()
    }

    fun getLongitude(): String {
        return storage.getString("longitude", "-63.182046728628706")!!
    }

    fun setRadius(radius: Long) {
        storage.edit().putLong("radius", radius).apply()
    }

    fun getRadius(): Long {
        return storage.getLong("radius", 15000)
    }
}