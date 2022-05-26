package com.example.proyecto_3_tetris_db.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.proyecto_3_tetris_db.R
import com.example.proyecto_3_tetris_db.databinding.ActivityGameOverBinding

class GameOverActivity : AppCompatActivity() {

    val PREFS = "game_settings"
    private lateinit var binding: ActivityGameOverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameOverBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val settings = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        var params = intent.extras
        val record = settings.getInt("record", 0)
        val points = params?.getInt("points")

        if (points != null && points > record) {
            binding.txtNewRecord
            binding.txtNewRecord.text = getResources().getString(R.string.new_record) + " " + points
            binding.txtNewRecord.visibility = View.VISIBLE
            settings.edit().putInt("record", points).commit()
        }

        binding.txtRecord.text = getResources().getString(R.string.record_label) + " " + record
        binding.txtPoints.text = getResources().getString(R.string.points_text) + " " + points

        binding.newGame.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.exitGame.setOnClickListener {
            finish()
        }
    }
}
