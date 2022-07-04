package com.moviles.marketplace.api.retrofit


import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFit {
    private val retrofit: Retrofit
    private lateinit var retrofitService: RetroFitService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("http://marketplace.jmacboy.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()

        RetrofitServiceCreate()
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptro())
            .build()
    }

    fun RetrofitServiceCreate(){
        retrofitService = retrofit.create(RetroFitService::class.java)
    }

    fun getRetrofitService(): RetroFitService {
        return retrofitService
    }
}

class HeaderInterceptro : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "Bearer " + sharedPref.getToken()
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader(
                "Authorization",
                token
            )
            .build()
        return chain.proceed(request)
    }
}