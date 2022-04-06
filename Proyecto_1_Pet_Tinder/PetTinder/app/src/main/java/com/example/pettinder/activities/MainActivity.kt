package com.example.pettinder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pettinder.R
import com.example.pettinder.adapters.ItemsMascotaAdapter
import com.example.pettinder.models.Mascota

class MainActivity : AppCompatActivity() {

    lateinit var btnLike: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        lstPersonas = findViewById(R.id.lstPersonas)

        btnLike = findViewById(R.id.btnLike)

        setupEventListener()
        setupItemsMascotas()
    }

    private fun setupItemsMascotas(){
        val itemsMascotas = arrayOf(
            Mascota(1,"Jake", "1 Año", "/img/jake.png"),
            Mascota(1,"Garfield", "4 Meses", "/img/jake.png")
        )
//        val adapter = ItemsMascotaAdapter(this, "layout", itemsMascotas)
//        lstPersonas.adapter = adapter
    }

    private fun setupEventListener() {
        btnLike.setOnClickListener{
            val intent = Intent(this, DescriptionPetActivity::class.java)

            //para enviar datos de Activity 1 al 2
            //intent.putExtra("varName", nombre)

            startActivity(intent)
        }
    }


}