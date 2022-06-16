package com.example.proyecto_4_mapeo_rutas.api.punto

import com.example.proyecto_4_mapeo_rutas.models.Punto
import retrofit2.Call
import retrofit2.http.*

interface JsonPuntoService {
    @GET("/api/rutas/{idRuta}/puntos")
    fun getPuntosByRuta(@Path("idRuta") id: Long): Call<ArrayList<Punto>>
}