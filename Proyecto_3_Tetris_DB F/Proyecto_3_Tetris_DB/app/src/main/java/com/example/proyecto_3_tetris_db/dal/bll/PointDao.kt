package com.example.proyecto_3_tetris_db.dal.bll

import androidx.room.*
import com.example.proyecto_3_tetris_db.dal.models.Point

@Dao
interface PointDao {
    @Query("SELECT * FROM point")
    fun selectAll(): List<Point>

    @Insert
    fun insert(point: Point): Long

    @Update
    fun update(point: Point)

    @Query("SELECT * FROM point WHERE id = :id")
    fun selectById(id: String): Point

    @Delete
    fun delete(point: Point)

    @Query("DELETE FROM point")
    fun deleteAll()

}