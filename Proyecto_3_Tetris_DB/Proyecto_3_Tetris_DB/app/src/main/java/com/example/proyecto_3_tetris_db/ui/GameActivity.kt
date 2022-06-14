package com.example.proyecto_3_tetris_db.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.proyecto_3_tetris_db.databinding.ActivityGameBinding
import com.example.proyecto_3_tetris_db.tetris.TGame
class GameActivity : AppCompatActivity() {

    private lateinit var tGame: TGame
    private lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        tGame = TGame(this,binding.gridLayoutGame)
        binding.button.text = "End"
        binding.button.setOnClickListener {
            tGame.end()
        }
        binding.btnDown.setOnClickListener {
            tGame.moveDown()
        }
        binding.btnLeft.setOnClickListener {
            tGame.moveLeft()
        }
        binding.btnRigth.setOnClickListener {
            tGame.moveRight()
        }
        binding.btnRotate.setOnClickListener {
            tGame.rotate()
        }
    }



    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        tGame.stop()
        binding.button.text = "Start"

    }

}