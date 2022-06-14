package com.example.proyecto_3_tetris_db.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.proyecto_3_tetris_db.dal.conn.AppDatabase
import com.example.proyecto_3_tetris_db.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.newGame.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        var shape = AppDatabase.getInstance(this).shapeDao().selectById("current")
        if(shape!=null){
            startActivity(Intent(this, GameActivity::class.java))
        }
    }


}
