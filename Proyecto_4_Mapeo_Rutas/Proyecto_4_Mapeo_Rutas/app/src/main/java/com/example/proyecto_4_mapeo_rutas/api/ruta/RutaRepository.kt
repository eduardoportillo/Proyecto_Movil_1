package com.example.proyecto_4_mapeo_rutas.api.ruta

import com.example.proyecto_4_mapeo_rutas.models.Response
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
                listener.onRutaListError(t)
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
                listener.onRutaCreateError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Ruta>,
                response: retrofit2.Response<Ruta>
            ) {
                listener.createRutaReady(response.body()!!)
            }
        })
    }

    fun updateRuta(id:Long, ruta: Ruta, listener: UpdateRutaListener) {
        retrofitService.updateRuta(ruta, id).enqueue(object : retrofit2.Callback<Ruta> {
            override fun onFailure(call: retrofit2.Call<Ruta>, t: Throwable) {
                listener.onRutaUpdateError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Ruta>,
                response: retrofit2.Response<Ruta>
            ) {
                listener.UpdateRutaReady(response.body()!!)
            }
        })
    }

    fun deleteRuta(id:Long, listener: DeleteRutaListener) {
        retrofitService.deleteRuta(id).enqueue(object : retrofit2.Callback<Response> {
            override fun onFailure(call: retrofit2.Call<Response>, t: Throwable) {
                listener.onRutaDeleteError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Response>,
                response: retrofit2.Response<Response>
            ) {
                listener.deleteRutaReady(response.body()!!)
            }
        })
    }

    interface RutaListener {
        fun getListaRutaReady(ruta: ArrayList<Ruta>)
        fun onRutaListError(t: Throwable)
    }

    interface CreateRutaListener {
        fun createRutaReady(ruta: Ruta)
        fun onRutaCreateError(t: Throwable)
    }

    interface UpdateRutaListener {
        fun  UpdateRutaReady(ruta: Ruta)
        fun onRutaUpdateError(t: Throwable)
    }

    interface DeleteRutaListener {
        fun deleteRutaReady(res: Response)
        fun onRutaDeleteError(t: Throwable)
    }

}