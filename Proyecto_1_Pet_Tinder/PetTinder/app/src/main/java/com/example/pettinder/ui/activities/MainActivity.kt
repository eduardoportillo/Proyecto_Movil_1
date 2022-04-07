package com.example.pettinder.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pettinder.R
import com.example.pettinder.models.Mascota
import com.example.pettinder.ui.adapters.MascotaAdapter
import com.example.pettinder.ui.adapters.MascotaListEventListener

class MainActivity : AppCompatActivity(), MascotaListEventListener {
    private lateinit var adapter: MascotaAdapter
    lateinit var lstMascotas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstMascotas = findViewById(R.id.lstMascotas)

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val itemsMascotas = arrayListOf(
            Mascota(1,"Jake", "1 Año", "Un Perrito muy hermoso", 70207967,"img/perro"),
            Mascota(2,"Garfield", "4 Meses", "Un Gatito muy hermoso",70207967, "img/gato")
        )
        val layoutManager = LinearLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )
        lstMascotas.layoutManager = layoutManager
        adapter = MascotaAdapter(itemsMascotas,this)
        lstMascotas.adapter = adapter
    }

    override fun onMeGustaClick(mascota: Mascota) {
        val intent = Intent(this, DescriptionPetActivity::class.java)
        intent.putExtra("varMascota", mascota)
        startActivityForResult(intent, 1)
    }

    override fun onNoMeGustaClick(mascota: Mascota) {
        adapter.deletePersona(mascota)
    }


}