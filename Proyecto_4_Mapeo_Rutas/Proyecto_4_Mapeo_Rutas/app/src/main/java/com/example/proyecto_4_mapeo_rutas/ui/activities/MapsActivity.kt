package com.example.proyecto_4_mapeo_rutas.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.proyecto_4_mapeo_rutas.R
import com.example.proyecto_4_mapeo_rutas.api.punto.PuntoRepository
import com.example.proyecto_4_mapeo_rutas.api.ruta.RutaRepository
import com.example.proyecto_4_mapeo_rutas.databinding.ActivityMapsBinding
import com.example.proyecto_4_mapeo_rutas.models.Punto
import com.example.proyecto_4_mapeo_rutas.models.Response
import com.example.proyecto_4_mapeo_rutas.models.Ruta
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, PuntoRepository.CreatePuntoListener,
    RutaRepository.VaciarPuntosListener {

    private var markerUser: Marker? = null
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    // Permisos Usuario
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION = 1

    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private val UPDATE_INTERVAL: Long = 5000 // Every 60 seconds.
    private val FASTEST_UPDATE_INTERVAL: Long = 2500 // Every 30 seconds
    private val MAX_WAIT_TIME = UPDATE_INTERVAL * 5 // Every 5 minutes.

    lateinit var puntos: ArrayList<Punto>
    var idRuta: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getCharSequenceArrayListExtra("VarPuntos")?.let { puntos = it as ArrayList<Punto> }
        intent.getLongExtra("VarIdRuta", -1)?.let { idRuta = it }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        createLocationRequest()
        setupLocationCallback()

        setupEvenListeners()
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest.create()

        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL)
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest.setMaxWaitTime(MAX_WAIT_TIME)
    }

    private fun setupLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    drawMarkerUser(location)
                    Log.d("UBICACION", "Trajo una ubicación ${location}")
                }
            }
        }
    }

    private fun setupEvenListeners() {
        binding.btnEnviarPunto.setOnClickListener { savePunto() }
        binding.btnVaciarPuntos.setOnClickListener { idRuta?.let { it1 ->
            RutaRepository().vaciarPuntos(
                it1,this)
        } }
    }

    private fun savePunto() {
        val dataPunto = Punto(
            latitud = markerUser?.position?.latitude.toString(),
            longitud = markerUser?.position?.longitude.toString(),
            ruta_id = idRuta
        )

        PuntoRepository().createPunto(dataPunto, this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        printPuntosByRuta(mMap)
        setupMyLocation()
    }

    fun printPuntosByRuta(mMap: GoogleMap) {
        if (puntos != null) {
            for (punto in puntos) {
                var latitudDouble = punto.latitud?.toDouble()
                var longitudDouble = punto.longitud?.toDouble()
                val latLng = LatLng(latitudDouble!!, longitudDouble!!)
                mMap.addMarker(MarkerOptions().position(latLng))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun setupMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), LOCATION_PERMISSION
            )
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location == null) {
                    return@addOnSuccessListener
                }
                drawMarkerUser(location)
            }
    }

    private fun drawMarkerUser(location: Location) {
        val myLocation = LatLng(location.latitude, location.longitude)
        if (markerUser == null) {
            markerUser = mMap.addMarker(
                MarkerOptions().position(myLocation).title("Mi ubicación").icon(
                    BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                )
            )
        } else {
            markerUser?.position = myLocation
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 17f))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isEmpty()) {
            return
        }
        if (requestCode == LOCATION_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupMyLocation()
            startLocationUpdates()
        }
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }


    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), LOCATION_PERMISSION
            )
            return
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun createPuntoReady(ruta: Punto) {
        finish();
        startActivity(getIntent());
    }

    override fun onPuntoCreateError(t: Throwable) {
        AlertDialog.Builder(this)
            .setTitle("Ops")
            .setMessage("Ocurrio un error al crear la ruta")
            .setPositiveButton(android.R.string.yes) { _, _ ->
                Toast.makeText(
                    applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton(android.R.string.no) { _, _ ->
                Toast.makeText(
                    applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT
                ).show()
            }
    }

    override fun vaciarPuntosReady(res: Response) {
        finish();
    }

    override fun onVaciarPuntosError(t: Throwable) {
        AlertDialog.Builder(this)
            .setTitle("Ops")
            .setMessage("Ocurrio un error al crear la ruta")
            .setPositiveButton(android.R.string.yes) { _, _ ->
                Toast.makeText(
                    applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton(android.R.string.no) { _, _ ->
                Toast.makeText(
                    applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT
                ).show()
            }
    }
}




