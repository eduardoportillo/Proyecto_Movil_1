package com.example.proyecto_3_tetris_db.dal.bll

import androidx.room.*
import com.example.proyecto_3_tetris_db.dal.models.ShapeDto

@Dao
interface ShapeDao {
    @Query("SELECT * FROM shape")
    fun selectAll(): List<ShapeDto>

    @Insert
    fun insert(shape: ShapeDto): Long

    @Update
    fun update(shape: ShapeDto)

    @Query("SELECT * FROM shape WHERE id = :id")
    fun selectById(id: String): ShapeDto

    @Delete
    fun delete(shape: ShapeDto)

    @Query("DELETE FROM shape")
    fun deleteAll()

}