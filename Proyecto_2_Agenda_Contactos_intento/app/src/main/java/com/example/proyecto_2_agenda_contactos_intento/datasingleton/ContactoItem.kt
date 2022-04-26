package com.example.proyecto_2_agenda_contactos_intento.datasingleton

import com.example.practicanavegacion.ui.models.Contacto
import com.example.practicanavegacion.ui.models.Telefono

object ContactoItem {
    var listContacto = arrayListOf<Contacto>()
    var tipoTelefono = arrayListOf<String>("oficina", "casa", "celular", "trabajo")

    var idContacto = 1
    var idTelefono = 1

    init {
        createContact("Eduardo", "Portillo", "/", "Santa Cruz", 24, "EPV@gmail.com","Remaso 1",70207967)
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
        idContacto++
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

    fun addTipoTelefono(Ttelefono: String) {
        tipoTelefono.add(Ttelefono)
    }
}