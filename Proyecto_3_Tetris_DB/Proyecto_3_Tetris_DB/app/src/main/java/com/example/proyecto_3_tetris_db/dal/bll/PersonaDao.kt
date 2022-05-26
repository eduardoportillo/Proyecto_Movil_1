package com.example.proyecto_3_tetris_db.dal.bll

import androidx.room.*

import com.example.proyecto_3_tetris_db.dal.models.Persona

@Dao
interface PersonaDao {
    @Query("SELECT * FROM persona")
    fun selectAll(): List<Persona>

    @Insert
    fun insert(persona: Persona): Long

    @Update
    fun update(persona: Persona)

    @Query("SELECT * FROM persona WHERE id = :id")
    fun selectById(id: Int): Persona

    @Delete
    fun delete(persona: Persona)
}