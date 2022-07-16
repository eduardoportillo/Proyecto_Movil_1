package com.moviles.marketplace.ui.activities.chat

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.moviles.marketplace.MarketPlaceApplication
import com.moviles.marketplace.R
import com.moviles.marketplace.api.ChatRepository
import com.moviles.marketplace.databinding.ActivityMapsMenssageBinding
import com.moviles.marketplace.models.Location
import com.moviles.marketplace.models.Menssage
import com.moviles.marketplace.ui.fragments.MapsFragment

class MapsMenssageActivity : AppCompatActivity(), OnMapReadyCallback, ChatRepository.AddLocationWithChatListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsMenssageBinding
    private var globalMarker: LatLng = LatLng(-17.8145819, -63.1560853)
    private var zoom: Float = 10.0f

    private var idChat: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsMenssageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        intent.extras?.let {
            idChat = it.getLong("idChat")
        }

        setupButton()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((globalMarker), zoom))
        mMap.isMyLocationEnabled = true
        mMap.setOnCameraMoveListener {
            globalMarker = mMap.cameraPosition.target
        }
    }

    private fun setupButton() {
        binding.btnSetCordenadasChat.setOnClickListener {
            val location = Location(
                latitude = globalMarker.latitude.toString(),
                longitude = globalMarker.longitude.toString(),
            )

            val msg = Menssage(
                chat_id = idChat,
                location = location,
            )
            ChatRepository().addLocationWithChat(msg, this)
        }
    }

    override fun addLocationWithChatReady(menssage: Menssage) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("idChat", idChat)
        startActivity(intent)
    }

    override fun oAddLocationWithChatError(t: Throwable) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("idChat", idChat)
        startActivity(intent)
    }
}