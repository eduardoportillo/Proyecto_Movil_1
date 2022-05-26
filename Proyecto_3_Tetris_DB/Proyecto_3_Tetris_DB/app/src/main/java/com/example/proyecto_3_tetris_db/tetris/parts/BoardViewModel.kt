package com.example.proyecto_3_tetris_db.tetris.parts

import androidx.lifecycle.ViewModel

class BoardViewModel() : ViewModel() {

//    val ROW = 36
//    val COL = 20

    val ROW = 20
    val COL = 9

    var board = Array(ROW) {
        Array(COL) { 0 }
    }

}