package com.example.proyecto_3_tetris_db.tetris

import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_3_tetris_db.R

class TBoard(
     var parentActivity: AppCompatActivity,
     var grid: GridLayout,
     var rowCount:Int = 25,
     var columnCount:Int = 9,
     var imageWidth:Int = 70,
     var imageHeight:Int = 70,
    ) {

    var imageMatrix = Array(rowCount) {
        arrayOfNulls<ImageView>(columnCount)
    }

    init {
        grid.rowCount = rowCount
        grid.columnCount = columnCount
        createImageMatrix()
    }

    fun createImageMatrix(){
        val inflater = LayoutInflater.from(parentActivity)
        for (i in 0 until rowCount) {
            for (j in 0 until columnCount) {
                val img = inflater.inflate(R.layout.inflate_image_view, grid, false) as ImageView
                img.layoutParams.width = imageWidth
                img.layoutParams.height = imageHeight
                img.requestLayout()
                imageMatrix!![i][j] = img
                grid.addView(img)
            }
        }
    }
    fun clearScreen() {
        for (i in 0 until rowCount)
            for (j in 0 until columnCount)
                imageMatrix[i][j]!!.setImageResource(getPixel(0))
    }

    fun getPixel(id: Int): Int {
        return when (id) {
            0 -> R.drawable.blackg1
            1 -> R.drawable.p1
            2 -> R.drawable.p2
            3 -> R.drawable.p3
            4 -> R.drawable.p4
            5 -> R.drawable.p5
            6 -> R.drawable.p6
            7 -> R.drawable.p7
            else -> R.drawable.p7
        }
    }




}