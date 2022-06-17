package com.example.proyecto_4_mapeo_rutas.api.punto

import com.example.proyecto_4_mapeo_rutas.models.Punto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PuntoRepository {

    private lateinit var retrofitService: JsonPuntoService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maparutas.jmacboy.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofit.create(JsonPuntoService::class.java)
    }

    fun getPuntosByRuta(idRuta: Long,listener: PuntoListener) {
        retrofitService.getPuntosByRuta(idRuta).enqueue(object : retrofit2.Callback<ArrayList<Punto>> {
            override fun onFailure(call: retrofit2.Call<ArrayList<Punto>>, t: Throwable) {
                listener.onPuntoListError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<ArrayList<Punto>>,
                response: retrofit2.Response<ArrayList<Punto>>
            ) {
                listener.getListaPuntoReady(response.body()!!)
            }
        })
    }

    fun createPunto(punto: Punto, listener: CreatePuntoListener) {
        retrofitService.createPunto(punto).enqueue(object : retrofit2.Callback<Punto> {
            override fun onFailure(call: retrofit2.Call<Punto>, t: Throwable) {
                listener.onPuntoCreateError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Punto>,
                response: retrofit2.Response<Punto>
            ) {
                listener.createPuntoReady(response.body()!!)
            }
        })
    }

    interface PuntoListener {
        fun getListaPuntoReady(puntos: ArrayList<Punto>)
        fun onPuntoListError(t: Throwable)
    }

    interface CreatePuntoListener {
        fun createPuntoReady(ruta: Punto)
        fun onPuntoCreateError(t: Throwable)
    }
}