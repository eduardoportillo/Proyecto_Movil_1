package com.example.practicanavegacion.ui.models

import java.io.Serializable

class Contacto (
    var id: Int,
    var nombre: String,
    var Apellido: String,
    var url_foto: String,
    var ciudad: String,
    var edad: Int,
    var email: String,
    var Direccion: String,
    var telefonos: ArrayList<Telefono> = arrayListOf<Telefono>()
): Serializable