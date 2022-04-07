package com.example.pettinder.models

import java.io.Serializable

data class Mascota(
    var id: Int,
    var nombre: String,
    var edad: String,
    var descripcion: String,
    var telefono: Int,
    var key_drawable: String

): Serializable
