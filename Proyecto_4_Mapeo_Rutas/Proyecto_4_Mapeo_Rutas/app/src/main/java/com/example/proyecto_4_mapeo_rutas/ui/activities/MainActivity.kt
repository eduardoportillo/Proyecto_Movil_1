package com.example.proyecto_4_mapeo_rutas.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_4_mapeo_rutas.databinding.ActivityMainBinding
import com.example.proyecto_4_mapeo_rutas.ui.adapters.RutaAdapter
import com.example.proyecto_4_mapeo_rutas.api.ruta.RutaRepository
import com.example.proyecto_4_mapeo_rutas.models.Ruta


class MainActivity : AppCompatActivity(), RutaRepository.RutaListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupEvenListeners()
    }

    private fun setupEvenListeners() {
        binding.btnAgregar.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchRutaList()
    }
    private fun fetchRutaList() {
        RutaRepository().getRutasbyUser(this)
    }



    override fun getListaRutaReady(data: ArrayList<Ruta>) {
        Log.d("JSON", data.toString())
        val adapter = RutaAdapter(data)
        binding.lstPosts.layoutManager = LinearLayoutManager(this)
        binding.lstPosts.adapter = adapter
    }



    override fun onError(t: Throwable) {
        Log.e("ERROR", t.message.toString())
    }
}