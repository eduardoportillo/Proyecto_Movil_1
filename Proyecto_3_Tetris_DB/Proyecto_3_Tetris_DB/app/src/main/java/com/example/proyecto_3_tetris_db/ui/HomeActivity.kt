package com.example.proyecto_3_tetris_db.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
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
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.settings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.continueGame.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("continue", false)
            startActivity(i)
        }
    }

//    override fun onStart() {
//        super.onStart()
//        if (GameState.saved) {
//            binding.continueGame.visibility = View.VISIBLE
//        }
//    }

}
