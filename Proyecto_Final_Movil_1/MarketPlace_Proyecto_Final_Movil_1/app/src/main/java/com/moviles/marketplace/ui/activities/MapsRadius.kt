package com.moviles.marketplace.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.LatLng
import com.moviles.marketplace.R
import com.moviles.marketplace.databinding.ActivityMapsRadiusBinding

class MapsRadius : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsRadiusBinding
    private var globalMarker: LatLng = LatLng(-17.8145819, -63.1560853)
    private var globalRadius: Double = 1000.0
    private var zoom: Float = 5000.0f
    private var circle: Circle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsRadiusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(globalMarker, zoom))
        mMap.setOnCameraMoveListener {
            globalMarker = mMap.cameraPosition.target
            drawCircle(globalMarker)
        }


    }

    private fun setupMyLocation() {
        TODO("Not yet implemented")
    }

    private fun drawCircle(point: LatLng) {
        if (this.circle != null) {
            this.circle?.remove()
        }
        this.circle = mMap.addCircle(
            com.google.android.gms.maps.model.CircleOptions()
                .center(point)
                .radius(globalRadius)
                .strokeColor(0x00ff0000)
                .fillColor(0x30ff0000)
        )
    }
}
