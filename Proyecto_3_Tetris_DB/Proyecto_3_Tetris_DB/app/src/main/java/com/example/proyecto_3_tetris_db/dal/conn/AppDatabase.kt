package com.example.proyecto_3_tetris_db.dal.conn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.proyecto_3_tetris_db.dal.bll.PointDao
import com.example.proyecto_3_tetris_db.dal.bll.ShapeDao
import com.example.proyecto_3_tetris_db.dal.models.Point
import com.example.proyecto_3_tetris_db.dal.models.ShapeDto

@Database(entities = [Point::class, ShapeDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pointDao():PointDao
    abstract fun shapeDao():ShapeDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "tetris"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}