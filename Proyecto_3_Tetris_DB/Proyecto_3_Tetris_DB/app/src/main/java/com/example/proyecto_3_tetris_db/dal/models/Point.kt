package com.example.proyecto_3_tetris_db.dal.models
import androidx.appcompat.app.AppCompatActivity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto_3_tetris_db.dal.conn.AppDatabase
import com.example.proyecto_3_tetris_db.tetris.TBoard

@Entity(tableName = "point")
 class Point(
    var x:Int,
    var y:Int,
    var color:Int,
    id:String = x.toString()+"-"+y.toString(),
) {
    @PrimaryKey()
    var id: String = id

    fun draw(tBoard:TBoard){
        tBoard.imageMatrix[y][x]!!.setImageResource(color)
    }

    fun updateDb(context: AppCompatActivity){
        var db = AppDatabase.getInstance(context)
        if(db.pointDao().selectById(id)==null){
            db.pointDao().insert(this)
        }else{
            db.pointDao().update(this)
        }
    }
    fun deleteDb(context: AppCompatActivity){
        var db = AppDatabase.getInstance(context)
        db.pointDao().delete(this)
    }
}