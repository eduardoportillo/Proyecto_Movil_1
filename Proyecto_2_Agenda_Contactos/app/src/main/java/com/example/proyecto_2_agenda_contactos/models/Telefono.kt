package com.example.proyecto_2_agenda_contactos.models

import java.io.Serializable

class Telefono (
    var id: Int,
    var numero: Int,
    var id_contacto: Int,
    var tipo_telefono: String
): Serializable