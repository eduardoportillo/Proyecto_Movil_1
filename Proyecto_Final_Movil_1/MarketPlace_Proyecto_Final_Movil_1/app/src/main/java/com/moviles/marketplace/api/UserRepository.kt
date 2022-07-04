package com.moviles.marketplace.api

import com.moviles.marketplace.api.retrofit.RetroFit
import com.moviles.marketplace.api.retrofit.RetroFitService
import com.moviles.marketplace.models.User

class UserRepository {
    private val retrofitService: RetroFitService

    init {
        retrofitService = RetroFit.getRetrofitService()
    }

    fun createUser(user: User, listener: CreateUserListener){
        retrofitService.registerUser(user).enqueue(object : retrofit2.Callback<User> {
            override fun onFailure(call: retrofit2.Call<User>, t: Throwable) {
                listener.onUserRegisterError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<User>,
                response: retrofit2.Response<User>
            ) {
                listener.RegisterUserReady(response.body()!!)
            }
        })
    }

    fun loginUser(user: User, listener: LoginUserListener){
        retrofitService.loginUser(user).enqueue(object : retrofit2.Callback<User> {
            override fun onFailure(call: retrofit2.Call<User>, t: Throwable) {
                listener.onUserLoginError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<User>,
                response: retrofit2.Response<User>
            ) {
                if (response.isSuccessful) {
                    listener.LoginUserReady(response.body()!!)
                } else if (response.code() == 401) {
                    listener.onUserLoginError(Throwable("unauthorized"))
                } else {
                    listener.onUserLoginError(Throwable("Error desconocido"))
                }
            }
        })
    }

    fun getUser(listener: GetUserListener){
        retrofitService.getUser().enqueue(object : retrofit2.Callback<User> {
            override fun onFailure(call: retrofit2.Call<User>, t: Throwable) {
                listener.onUserGetError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<User>,
                response: retrofit2.Response<User>
            ) {
                listener.getUserReady(response.body()!!)
            }
        })
    }


    interface CreateUserListener {
        fun RegisterUserReady(user: User)
        fun onUserRegisterError(t: Throwable)
    }

    interface LoginUserListener {
        fun LoginUserReady(user: User)
        fun onUserLoginError(t: Throwable)
    }

    interface GetUserListener {
        fun getUserReady(user: User)
        fun onUserGetError(t: Throwable)
    }

}