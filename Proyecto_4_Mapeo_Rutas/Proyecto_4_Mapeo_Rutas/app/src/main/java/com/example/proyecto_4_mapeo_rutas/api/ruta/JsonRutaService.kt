package com.example.proyecto_4_mapeo_rutas.api.ruta

import com.example.proyecto_4_mapeo_rutas.models.Response
import com.example.proyecto_4_mapeo_rutas.models.Ruta
import retrofit2.Call
import retrofit2.http.*

interface JsonRutaService {
    @GET("/api/users/12/rutas")
    fun getRutasbyUser(): Call<ArrayList<Ruta>>

    @POST("/api/rutas")
    fun createRuta(@Body ruta: Ruta): Call<Ruta>

    @PATCH("/api/rutas/{id}") // sintaxis de ruta /api/rutas/id_ruta
    fun updateRuta(@Body ruta: Ruta, @Path("id") id: Long): Call<Ruta>

    @DELETE("/api/rutas/{id}")
    fun deleteRuta(@Path("id") id: Long): Call<Response>

    @DELETE("/api/rutas/{id}/vaciar")
    fun vaciarPuntos(@Path("id") id: Long): Call<Response>
}