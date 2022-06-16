package com.example.proyecto_4_mapeo_rutas.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Punto(
    var id: Long? = null,
    var latitud: String? = null,
    var longitud:  String? = null,
    var ruta_id: Long? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
) : Serializable
