package com.example.proyecto_4_mapeo_rutas.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_4_mapeo_rutas.api.punto.PuntoRepository
import com.example.proyecto_4_mapeo_rutas.databinding.ActivityMainBinding
import com.example.proyecto_4_mapeo_rutas.ui.adapters.RutaAdapter
import com.example.proyecto_4_mapeo_rutas.api.ruta.RutaRepository
import com.example.proyecto_4_mapeo_rutas.models.Punto
import com.example.proyecto_4_mapeo_rutas.models.Response
import com.example.proyecto_4_mapeo_rutas.models.Ruta


class MainActivity : AppCompatActivity(), RutaRepository.RutaListener,
    RutaAdapter.RutaListEventListener, RutaRepository.DeleteRutaListener,
    PuntoRepository.PuntoListener {

    private lateinit var binding: ActivityMainBinding
    var idRuta: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupEvenListeners()
    }

    override fun onResume() {
        super.onResume()
        fetchRutaList()
    }

    private fun setupEvenListeners() {
        binding.btnAgregar.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchRutaList() {
        RutaRepository().getRutasbyUser(this)
    }

    private fun fetchPuntosList(idRuta: Long) {
        this.idRuta = idRuta
        PuntoRepository().getPuntosByRuta(idRuta, this)
    }

    override fun getListaRutaReady(data: ArrayList<Ruta>) {
        Log.d("JSON", data.toString())
        val adapter = RutaAdapter(data, this)
        binding.lstPosts.layoutManager = LinearLayoutManager(this)
        binding.lstPosts.adapter = adapter
    }

    override fun onRutaListError(t: Throwable) {
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

    override fun onEditClick(ruta: Ruta) {
        val intent = Intent(this, FormActivity::class.java)
        intent.putExtra("varRuta", ruta)
        startActivity(intent)
    }

    override fun onDeleteClick(ruta: Ruta) {
        RutaRepository().deleteRuta(ruta.id!!, this)
    }

    override fun onVerPuntosClick(idRuta: Long) {

        fetchPuntosList(idRuta)
    }

    override fun deleteRutaReady(res: Response) {
        fetchRutaList()
    }

    override fun onRutaDeleteError(t: Throwable) {
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

    override fun getListaPuntoReady(puntos: ArrayList<Punto>) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("VarIdRuta", idRuta)
        intent.putExtra("VarPuntos", puntos)
        startActivity(intent)
    }

    override fun onPuntoListError(t: Throwable) {
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