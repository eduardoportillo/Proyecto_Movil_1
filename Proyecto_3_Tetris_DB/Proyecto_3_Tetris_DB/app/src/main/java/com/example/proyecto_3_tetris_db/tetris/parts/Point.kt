package com.example.proyecto_3_tetris_db.tetris.parts

class Point(var x:Int,var y:Int){

    fun moveDown(){
        x++
    }
    fun moveLeft(){
        y--
    }

    fun moveRight(){
        y++
    }

    fun moveTop(){
        x--
    }
}