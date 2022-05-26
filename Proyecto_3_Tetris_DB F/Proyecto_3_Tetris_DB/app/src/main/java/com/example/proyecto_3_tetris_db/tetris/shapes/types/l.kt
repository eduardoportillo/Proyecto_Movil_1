package com.example.proyecto_3_tetris_db.tetris.shapes.types

import com.example.proyecto_3_tetris_db.R
import com.example.proyecto_3_tetris_db.tetris.TBoard
import com.example.proyecto_3_tetris_db.tetris.shapes.Shape

class l(tBoard: TBoard):Shape("default", tBoard, R.drawable.p3) {

    init {
        spaces[0][1]=1
        spaces[1][1]=1
        spaces[2][1]=1
        spaces[3][1]=1
        spaces[3][2]=1
    }
}