package com.example.proyecto_2_agenda_contactos.datasingleton

import com.example.proyecto_2_agenda_contactos.models.Contacto
import com.example.proyecto_2_agenda_contactos.models.Telefono

object ContactoItem {
    var listContacto = arrayListOf<Contacto>()
    var tipoTelefono = arrayListOf("oficina", "casa", "celular", "trabajo")

    private var idContacto = 0
    private var idTelefono = 0

    init {
        createContact(
            "Eduardo",
            "Portillo",
            "/",
            "Santa Cruz",
            24,
            "EPV@gmail.com",
            "Remaso 1",
            70207967
        )
        createContact(
            "Pepe",
            "Palotes",
            "/",
            "Santa Cruz",
            24,
            "EPV@gmail.com",
            "Remaso 1",
            70207967
        )
    }

    fun createContact(
        nombre: String,
        apellido: String,
        url_foto: String,
        ciudad: String,
        edad: Int,
        email: String,
        direccion: String,
        telefono: Int
    ) {
        listContacto.add(
            Contacto(
                idContacto,
                nombre,
                apellido,
                url_foto,
                ciudad,
                edad,
                email,
                direccion
            )
        )
        addTelefono(idContacto, telefono, 0)
        idContacto++
    }

    fun removeContact(contacto: Contacto) {
        listContacto.remove(contacto)
    }

    fun editContact(
        contactoId: Int,
        nombre: String,
        apellido: String,
        ciudad: String,
        edad: Int,
        email: String,
        direccion: String
    ) {
        for (contacto in listContacto) {
            if (contacto.id == contactoId) {
                val contactoaEditar = listContacto.get(contactoId)
                contactoaEditar.nombre = nombre
                contactoaEditar.apellido = apellido
                contactoaEditar.ciudad = ciudad
                contactoaEditar.edad = edad
                contactoaEditar.email = email
                contactoaEditar.direccion = direccion

            }
        }
    }

    fun addTelefono(idContacto: Int, numerotelefono: Int, idTelefonotipo: Int) {

        listContacto.get(idContacto).telefonos.add(
            Telefono(
                idTelefono,
                numerotelefono,
                idContacto,
                tipoTelefono.get(idTelefonotipo)
            )
        )
        idTelefono++
    }

    fun getTelefonoByContacto(contactoId: Int): ArrayList<Telefono> {
        lateinit var telefonoContacto: ArrayList<Telefono>
        for (contacto in listContacto) {
            if (contacto.id == contactoId) {
                telefonoContacto = listContacto.get(contactoId).telefonos
            }
        }
        return telefonoContacto
    }


    fun addTipoTelefono(Ttelefono: String) {
        tipoTelefono.add(Ttelefono)
    }
}