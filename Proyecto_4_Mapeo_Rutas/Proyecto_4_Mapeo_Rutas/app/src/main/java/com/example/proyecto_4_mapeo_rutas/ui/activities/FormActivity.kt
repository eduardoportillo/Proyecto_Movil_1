package com.example.proyecto_4_mapeo_rutas.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_4_mapeo_rutas.api.ruta.RutaRepository
import com.example.proyecto_4_mapeo_rutas.databinding.ActivityFormBinding
import com.example.proyecto_4_mapeo_rutas.models.Ruta

class FormActivity : AppCompatActivity(), RutaRepository.CreateRutaListener,
    RutaRepository.UpdateRutaListener {

    var ruta: Ruta? = null
    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        intent.getSerializableExtra("varRuta") ?.let { ruta = it as  Ruta}

        loadRutaData()
        setupEvenListeners()

    }

    private fun setupEvenListeners() {
        binding.btnSave.setOnClickListener { saveRuta() }
        binding.btnCancel.setOnClickListener { finish() }
    }

    private fun loadRutaData() {
        if (ruta == null) {
            return
        }
        binding.txtNombre.setText(ruta?.nombre)
    }

    private fun saveRuta() {
        val rutaData = Ruta(
            nombre = binding.txtNombre.text.toString(),
            user_id = 12
        )
        if (ruta == null) {
            RutaRepository().createRuta(rutaData, this)
        } else {
            ruta!!.id?.let { RutaRepository().updateRuta(it, rutaData, this) }
        }
    }

    override fun createRutaReady(ruta: Ruta) {
        finish()
    }

    override fun onRutaCreateError(t: Throwable) {
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

    override fun UpdateRutaReady(ruta: Ruta) {
        finish()
    }

    override fun onRutaUpdateError(t: Throwable) {
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