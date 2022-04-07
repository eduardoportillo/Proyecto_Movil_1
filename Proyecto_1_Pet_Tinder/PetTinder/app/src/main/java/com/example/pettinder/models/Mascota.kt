package com.example.pettinder.models

import java.io.Serializable

data class Mascota(
    var id: Int,
    var nombre: String,
    var edad: String,
    var descripcion: String,
    var telefono: Int,
    var keyDrawableFotoPerfil: String,
    var keyDrawableGaleria1: String,
    var keyDrawableGaleria2: String,
    var keyDrawableGaleria3: String

): Serializable
