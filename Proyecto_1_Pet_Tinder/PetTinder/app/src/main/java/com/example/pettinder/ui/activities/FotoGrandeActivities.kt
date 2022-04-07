package com.example.pettinder.ui.activities

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.pettinder.R

class FotoGrandeActivities : AppCompatActivity() {

    lateinit var btnVolverDescripcion: Button
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto_grande_activities)

        btnVolverDescripcion = findViewById(R.id.btnVolverDescripcion)
        imageView = findViewById(R.id.imageView)

        setData()
        setupEventListener()
    }

    fun setData(){
        var key_drawable_temp = intent.getSerializableExtra("varGaleria")
        val imageResource: Int = getResources().getIdentifier(key_drawable_temp as String?, "drawable", packageName)
        var dra: Drawable? = getDrawable(imageResource)
        imageView.setImageDrawable(dra)
    }
    fun setupEventListener(){
        btnVolverDescripcion.setOnClickListener{finish()}
    }
}