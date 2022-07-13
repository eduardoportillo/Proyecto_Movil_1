package com.moviles.marketplace.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.moviles.marketplace.R

class MapsFragment() : Fragment() {

    var globalMarker: LatLng = LatLng(-17.8145819, -63.1560853)
    private var zoom: Float = 10.0f

    lateinit var latLngListener: latLngEventListener

    private val callback = OnMapReadyCallback { mMap ->
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((globalMarker), zoom))
        mMap.isMyLocationEnabled = true
        mMap.setOnCameraMoveListener {
            latLngListener.latLngSend(globalMarker.latitude.toString(), globalMarker.longitude.toString())
            globalMarker = mMap.cameraPosition.target
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        latLngListener.latLngSend(globalMarker.latitude.toString(), globalMarker.longitude.toString())
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            latLngListener = activity as latLngEventListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement onSomeEventListener")
        }
    }

    interface latLngEventListener{
        fun latLngSend(latitude: String, longitude: String)
    }
}