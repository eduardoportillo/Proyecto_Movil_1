package com.example.proyecto_4_mapeo_rutas.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_4_mapeo_rutas.api.ruta.RutaRepository
import com.example.proyecto_4_mapeo_rutas.databinding.ActivityFormBinding
import com.example.proyecto_4_mapeo_rutas.models.Ruta

class FormActivity : AppCompatActivity(), RutaRepository.CreateRutaListener{
    private lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupEvenListeners()
    }

    private fun setupEvenListeners() {
        binding.btnSave.setOnClickListener {
            val ruta = Ruta(
                nombre = binding.txtNombre.text.toString(),
                user_id = 12
            )
            RutaRepository().createRuta(ruta, this)
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    override fun createRutaReady(ruta: Ruta) {
        finish()
    }

    override fun onError(t: Throwable) {
        Log.e("ERROR", t.message.toString())
    }
}