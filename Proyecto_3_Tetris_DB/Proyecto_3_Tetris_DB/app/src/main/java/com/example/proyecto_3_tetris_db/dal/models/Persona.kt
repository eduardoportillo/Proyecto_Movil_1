package com.example.proyecto_3_tetris_db.dal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Persona(
    var nombres: String,
    var apellidos: String,
    var edad: Int,
    @ColumnInfo(name = "fecha_nacimiento") var fechaNacimiento: String,
    var telefono: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}