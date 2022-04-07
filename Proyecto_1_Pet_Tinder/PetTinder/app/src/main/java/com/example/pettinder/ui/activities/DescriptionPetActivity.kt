package com.example.pettinder.ui.activities

import android.content.Intent
import android.graphics.drawable.Drawable
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

        var key_drawable_temp = mascota.keyDrawableGaleria1
        val imageResource1: Int = getResources().getIdentifier(key_drawable_temp, "drawable", packageName)
        var dra1: Drawable? = getDrawable(imageResource1)
        binding.btnGaleriaImg1.setImageDrawable(dra1)

        key_drawable_temp = mascota.keyDrawableGaleria2
        val imageResource2: Int = getResources().getIdentifier(key_drawable_temp, "drawable", packageName)
        val dra2: Drawable? = getDrawable(imageResource2)
        binding.btnGaleriaImg2.setImageDrawable(dra2)

        key_drawable_temp = mascota.keyDrawableGaleria3
        val imageResource3: Int = getResources().getIdentifier(key_drawable_temp, "drawable", packageName)
        val dra3: Drawable? = getDrawable(imageResource3)
        binding.btnGaleriaImg3.setImageDrawable(dra3)



    }

    private fun setupEventListener() {
        binding.btnVolverMain.setOnClickListener { finish() }


        binding.btnGaleriaImg1.setOnClickListener{
            val intent = Intent(this, FotoGrandeActivities::class.java)
            intent.putExtra("varGaleria", mascota.keyDrawableGaleria1)
            startActivity(intent)
        }

        binding.btnGaleriaImg2.setOnClickListener{
            val intent = Intent(this, FotoGrandeActivities::class.java)
            intent.putExtra("varGaleria", mascota.keyDrawableGaleria2)
            startActivity(intent)
        }

        binding.btnGaleriaImg3.setOnClickListener{
            val intent = Intent(this, FotoGrandeActivities::class.java)
            intent.putExtra("varGaleria", mascota.keyDrawableGaleria3)
            startActivity(intent)
        }

    }
}