package com.example.pettinder.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pettinder.databinding.ActivityDescriptionPetBinding
import com.example.pettinder.models.Mascota

class DescriptionPetActivity : AppCompatActivity() {

    lateinit var mascota: Mascota
    private lateinit var binding: ActivityDescriptionPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionPetBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mascota = intent.getSerializableExtra("varMascota") as Mascota

        setData()
        setupEventListener()
    }

    private fun setData() {
        binding.lblNombreD.text = mascota.nombre
        binding.lblDescripcion.text = mascota.descripcion
        binding.lblTelefono.text = mascota.telefono.toString()

    }

    private fun setupEventListener() {
        binding.btnVolverMain.setOnClickListener { finish() }
    }
}