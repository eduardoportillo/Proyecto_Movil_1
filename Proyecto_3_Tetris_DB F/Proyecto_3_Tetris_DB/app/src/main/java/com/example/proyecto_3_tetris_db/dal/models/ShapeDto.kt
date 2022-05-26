package com.example.proyecto_3_tetris_db.dal.models
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto_3_tetris_db.dal.conn.AppDatabase

@Entity(tableName = "shape")
 class ShapeDto(
    var px:Int,
    var py:Int,
    id:String = "",
    var type:String,
) {
    @PrimaryKey()
    var id: String = id

    fun updateDb(context: AppCompatActivity){
        var db = AppDatabase.getInstance(context)
        if(db.shapeDao().selectById(id)==null){
            db.shapeDao().insert(this)
        }else{
            db.shapeDao().update(this)
        }
    }
}