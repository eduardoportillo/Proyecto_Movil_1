package com.example.pettinder.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pettinder.R

class DescriptionPetActivity : AppCompatActivity() {

    lateinit var btnVolverMain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_pet)

        btnVolverMain = findViewById(R.id.btnVolverMain)

        setupEventListener()

    }

    private fun setupEventListener() {
        btnVolverMain.setOnClickListener{
            finish()
            //val intent = Intent(this, MainActivity::class.java)
            //para recibir datos de Activity 1 al 2
            //val nombre = intent.getStringExtra("varNombre")
            //lblBienvenido.text = "Bienvenido" + nombre
            //startActivity(intent)
        }
    }
}