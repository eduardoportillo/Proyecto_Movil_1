package com.example.proyecto_3_tetris_db.tetris.shapes.types

import com.example.proyecto_3_tetris_db.R
import com.example.proyecto_3_tetris_db.tetris.TBoard
import com.example.proyecto_3_tetris_db.tetris.shapes.Shape

class default( tBoard: TBoard):Shape("default", tBoard,R.drawable.p1) {

    init {
        spaces[2][1]=1
        spaces[2][2]=1

        spaces[1][1]=1
        spaces[1][2]=1
    }
}