package com.example.proyecto_3_tetris_db.tetris

import android.widget.ImageView
import com.example.proyecto_3_tetris_db.tetris.parts.Part
import com.example.proyecto_3_tetris_db.tetris.parts.PartI

class GameState {
    companion object {
        var saved = false
        var points: Int = 0
        var board = Array(20) {
            Array(9) { 0 }
        }
        var part: Part = PartI(0, 0)

        fun resetState() {
            saved = false
            points = 0
            board = Array(20) {
                Array(9) { 0 }
            }
            part = PartI(0, 0)
        }

        fun saveState(p:Int, b:Array<Array<Int>>, pt: Part) {
            saved = true
            points = p
            board = b
            part = pt
        }
    }
}