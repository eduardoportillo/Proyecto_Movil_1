package com.example.proyecto_3_tetris_db.tetris.shapes.types

import com.example.proyecto_3_tetris_db.R
import com.example.proyecto_3_tetris_db.tetris.TBoard
import com.example.proyecto_3_tetris_db.tetris.shapes.Shape

class i(tBoard: TBoard):Shape("i", tBoard, R.drawable.p2) {

    init {
        spaces[0][1]=1
        spaces[1][1]=1
        spaces[2][1]=1
        spaces[3][1]=1
    }
}