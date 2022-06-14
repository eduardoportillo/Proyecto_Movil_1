package com.example.proyecto_4_mapeo_rutas.api.ruta

import com.example.proyecto_4_mapeo_rutas.models.Ruta
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RutaRepository {

    private lateinit var retrofitService: JsonRutaService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maparutas.jmacboy.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofit.create(JsonRutaService::class.java)
    }

    fun getRutasbyUser(listener: RutaListener) {
        retrofitService.getRutasbyUser().enqueue(object : retrofit2.Callback<ArrayList<Ruta>> {
            override fun onFailure(call: retrofit2.Call<ArrayList<Ruta>>, t: Throwable) {
                listener.onError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<ArrayList<Ruta>>,
                response: retrofit2.Response<ArrayList<Ruta>>
            ) {
                listener.getListaRutaReady(response.body()!!)
            }
        })
    }

    fun createRuta(ruta: Ruta, listener: CreateRutaListener) {
        retrofitService.createRuta(ruta).enqueue(object : retrofit2.Callback<Ruta> {
            override fun onFailure(call: retrofit2.Call<Ruta>, t: Throwable) {
                listener.onError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Ruta>,
                response: retrofit2.Response<Ruta>
            ) {
                listener.createRutaReady(response.body()!!)
            }
        })
    }



    interface RutaListener {
        fun getListaRutaReady(ruta: ArrayList<Ruta>)
        fun onError(t: Throwable)
    }

    interface CreateRutaListener {
        fun createRutaReady(ruta: Ruta)
        fun onError(t: Throwable)
    }

    interface UpdateRutaListener {
        fun  UpdateRutaReady(ruta: Ruta)
        fun onError(t: Throwable)
    }

    interface DeleteRutaListener {
        fun deleteRutaReady(ruta: Ruta)
        fun onError(t: Throwable)
    }

}