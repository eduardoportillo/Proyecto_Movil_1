package com.example.proyecto_4_mapeo_rutas.api.ruta

import com.example.proyecto_4_mapeo_rutas.models.Ruta
import retrofit2.Call
import retrofit2.http.*

interface JsonRutaService {
    @GET("/api/users/12/rutas")
    fun getRutasbyUser(): Call<ArrayList<Ruta>>

    @POST("/api/rutas")
    fun createRuta(@Body ruta: Ruta): Call<Ruta>

    @PATCH("/api/rutas") // sintaxis de ruta /api/rutas/id_ruta
    fun uptdateRuta(@Body ruta: Ruta): Call<Ruta>

    @DELETE("/api/rutas/5/")
    fun deleteRuta(@Body ruta: Ruta): Call<Ruta>
}