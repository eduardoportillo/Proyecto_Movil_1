package com.moviles.marketplace.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.LatLng
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.R
import com.moviles.marketplace.databinding.ActivityMapsRadiusBinding

class MapsRadius : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsRadiusBinding
    private var globalMarker: LatLng = LatLng(-17.8145819, -63.1560853)
    private var globalRadius: Double = sharedPref.getRadius().toDouble()
    private var zoom: Float = 10.0f
    private var maxRadius: Int = 30000
    private var minRadius: Int = 500
    private var circle: Circle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsRadiusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setupButton()
        setupSeekBar()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((globalMarker), zoom))
        mMap.isMyLocationEnabled = true
        drawCircle(globalMarker)
        mMap.setOnCameraMoveListener {
            globalMarker = mMap.cameraPosition.target
            drawCircle(globalMarker)
        }
    }

    private fun setupButton() {
        binding.btnSetCordenadas.setOnClickListener {
            sharedPref.setLatitude(globalMarker.latitude.toString())
            sharedPref.setLongitude(globalMarker.longitude.toString())
            sharedPref.setRadius(globalRadius.toLong())
            finish()
        }
    }

    private fun setupSeekBar() {
        binding.seekBarRadius.min = minRadius
        binding.seekBarRadius.max = maxRadius
        binding.seekBarRadius.progress = globalRadius.toInt()
        binding.seekBarRadius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                globalRadius = progress.toDouble()
                drawCircle(globalMarker)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
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
