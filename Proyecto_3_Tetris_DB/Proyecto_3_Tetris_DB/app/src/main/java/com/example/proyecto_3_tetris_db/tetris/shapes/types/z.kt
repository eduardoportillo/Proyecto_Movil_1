package com.example.proyecto_3_tetris_db.tetris.shapes.types

import com.example.proyecto_3_tetris_db.R
import com.example.proyecto_3_tetris_db.tetris.TBoard
import com.example.proyecto_3_tetris_db.tetris.shapes.Shape

class z(tBoard: TBoard):Shape("z", tBoard, R.drawable.p7) {

    init {
        spaces[1][2]=1
        spaces[2][1]=1
        spaces[2][2]=1
        spaces[3][1]=1
    }
}