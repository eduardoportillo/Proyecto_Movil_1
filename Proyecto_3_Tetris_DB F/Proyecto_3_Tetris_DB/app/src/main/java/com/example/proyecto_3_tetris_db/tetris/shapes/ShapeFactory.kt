package com.example.proyecto_3_tetris_db.tetris.shapes

import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_3_tetris_db.dal.conn.AppDatabase
import com.example.proyecto_3_tetris_db.tetris.shapes.types.*
import com.example.proyecto_3_tetris_db.tetris.TBoard

class ShapeFactory {
    companion object{
        var types:Array<String> = arrayOf(
            "default",
            "i",
            "l",
            "t",
            "z"
        )
        fun create(type:String="default", tBoard: TBoard): Shape {
            var instance: Shape
                when(type){
                    "i"->instance= i(tBoard)
                    "l"->instance= l(tBoard)
                    "t"->instance= t(tBoard)
                    "z"->instance= z(tBoard)
                    else ->instance= default(tBoard)
                }
            return instance;
        }
        fun createFromDB(context:AppCompatActivity, tBoard: TBoard):Shape?{
            var shape =  AppDatabase.getInstance(context).shapeDao().selectById("current")
            if(shape == null) {
                return null
            }
            var finalS =create(shape.type,tBoard)
            finalS.px = shape.px
            finalS.py = shape.py
            return finalS
        }
    }
}