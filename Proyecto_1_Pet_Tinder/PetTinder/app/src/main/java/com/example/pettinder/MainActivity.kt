package com.example.pettinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btnLike: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLike = findViewById(R.id.btnLike)

        setupEventListener()
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