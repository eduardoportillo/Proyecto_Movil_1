package com.example.pettinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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
            val intent = Intent(this, MainActivity::class.java)

            //para enviar datos de Activity 1 al 2
            //intent.putExtra("varName", nombre)

            startActivity(intent)
        }
    }
}