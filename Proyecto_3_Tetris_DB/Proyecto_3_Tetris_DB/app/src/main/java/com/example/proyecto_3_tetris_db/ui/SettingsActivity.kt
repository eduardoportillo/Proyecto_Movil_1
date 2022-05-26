package com.example.proyecto_3_tetris_db.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_3_tetris_db.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    val PREFS = "game_settings"

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val settings = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val speed = settings.getLong("speed", 200L)

        if (speed == 100L)
            binding.rdGameMode.check(binding.d3.id)
        if (speed == 200L)
            binding.rdGameMode.check(binding.d2.id)
        if (speed == 300L)
            binding.rdGameMode.check(binding.d1.id)

    }

    override fun onStop() {
        super.onStop()
        val settings = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val editor = settings.edit()

        when (binding.rdGameMode.checkedRadioButtonId) {
            binding.d1.id -> {
                editor.putLong("speed", 300)
            }
            binding.d2.id -> {
                editor.putLong("speed", 200)
            }
            binding.d3.id -> {
                editor.putLong("speed", 100)
            }
        }

        editor.commit()
    }
}
