package com.example.proyecto_2_agenda_contactos.models

import java.io.Serializable

class Contacto (
    var id: Int,
    var nombre: String,
    var apellido: String,
    var url_foto: String,
    var ciudad: String,
    var edad: Int,
    var email: String,
    var direccion: String,
    var telefonos: ArrayList<Telefono> = arrayListOf<Telefono>()
): Serializable