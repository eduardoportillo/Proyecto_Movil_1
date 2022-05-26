package com.example.proyecto_3_tetris_db.tetris

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyecto_3_tetris_db.tetris.parts.Part
import com.example.proyecto_3_tetris_db.tetris.parts.PartI

class GameStateViewModel(var points: Int = 0): ViewModel() {
    var notifyPoints = MutableLiveData<Int>()

    var board = Array(20) {
        Array(9) { 0 }
    }

    var part: Part = PartI(0, 0)

    fun addScore(p:Int) {
        points += p
        notifyPoints.value = points
    }

}