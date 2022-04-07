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
        var intent = Intent(this, DescriptionPetActivity::class.java)
        setupRecyclerView(intent)
    }

    private fun setupRecyclerView(intent: Intent) {
        var itemsMascotas = arrayListOf(
            Mascota(
                1,
                "Jake",
                "1 Año",
                "Un Perrito muy hermoso",
                70207967,
                "dachund",
                "dachshundgaleria1",
                "dachshundgaleria2",
                "dachshundgaleria3"
            ),
            Mascota(
                2,
                "Garfield",
                "4 Meses",
                "Un Gatito muy hermoso",
                70207967,
                "garfield",
                "garfieldgaleria1",
                "garfieldgaleria2",
                "garfieldgaleria3"
            ),
            Mascota(
                2,
                "Siberiano",
                "2 Años",
                "Un Perro Criado en Siberia",
                70207967,
                "husky",
                "huskygaleria1",
                "huskygaleria2",
                "huskygaleria3"
            )
        )
        val layoutManager = LinearLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )
        lstMascotas.layoutManager = layoutManager
        adapter = MascotaAdapter(itemsMascotas, this, this, intent)
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

    override fun scroll(mascota: Mascota, intent: Intent) {
        lstMascotas.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dx > 0) {
                    intent.putExtra("varMascota", mascota)
                    startActivityForResult(intent, 1)
                }


                else if (dx < 0) {
                    adapter.deletePersona(mascota)
                }
            }
        })
    }



}