package com.example.proyecto_3_tetris_db.tetris.shapes

import com.example.proyecto_3_tetris_db.dal.models.Point
import com.example.proyecto_3_tetris_db.dal.models.ShapeDto
import com.example.proyecto_3_tetris_db.tetris.TBoard


abstract class Shape(
    var id: String,
    var tBoard: TBoard,
    var color: Int,
    var px:Int = 0,
    var py:Int = -4,
) {
    var size:Int = 5

    var spaces = Array(size) {
       arrayOfNulls<Int>(size)
    }
    init {
      initMatrix()
    }

    fun toDto():ShapeDto{
        return ShapeDto(px,py,"current",id);
    }

    fun initMatrix(){
        for (i in 0 until size)
            for (j in 0 until size)
                spaces[i][j] = 0
    }

    fun moveDown(points:MutableList<Point>):Boolean{
        for (i in 0 until size)
            for (j in 0 until size)
                if (spaces[i][j]!! >0){
                    if(i+py+1 >= tBoard.rowCount) return false
                    for(point in points){
                        if(i+py+1 >= point.y && j+px == point.x) return false
                    }
                }
        py++
        return true
    }

    fun moveLeft(points:MutableList<Point>){
        for (i in 0 until size)
            for (j in 0 until size)
                if (spaces[i][j]!! >0){
                    if(j+px-1 < 0) return
                    for(point in points){
                        if(i+py == point.y && j+px-1 == point.x) return
                    }
                }
        px--
    }

    fun moveRight(points:MutableList<Point>){
        for (i in 0 until size)
            for (j in 0 until size)
                if (spaces[i][j]!! >0){
                    if(j+px+1 >= tBoard.columnCount) return
                    for(point in points){
                        if(i+py == point.y && j+px+1 == point.x) return
                    }
                }
        px++
    }

    fun rotate(){
        var nueva = Array(size) {
            arrayOfNulls<Int>(size)
        }
        for (x in 0 until size)
            for (y in 0 until size)
                nueva[y][size-1-x] = spaces[x][y]

        spaces = nueva
    }
    fun draw(){
        for (i in 0 until size)
            for (j in 0 until size)
                if(i+py < tBoard.rowCount && i+py >= 0 && j+px < tBoard.columnCount && j+px >= 0){
                    if (spaces[i][j]!! >0){
                        tBoard.imageMatrix[i+py][j+px]!!.setImageResource(color)
                    }
                }

    }

    fun getPoints():MutableList<Point>{
        var arr_shapes:MutableList<Point> = ArrayList()
        for (i in 0 until size)
            for (j in 0 until size)
                if(i+py < tBoard.rowCount && i+py >= 0 && j+px < tBoard.columnCount && j+px >= 0){
                    if (spaces[i][j]!! >0) {
                        arr_shapes.add(Point(j+px,i+py,color))
                    }
                }
        return arr_shapes
    }


}