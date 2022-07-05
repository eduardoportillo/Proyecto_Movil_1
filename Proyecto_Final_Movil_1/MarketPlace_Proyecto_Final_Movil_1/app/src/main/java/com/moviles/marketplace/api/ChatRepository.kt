package com.moviles.marketplace.api

import com.example.marketplace.models.Chat
import com.moviles.marketplace.api.retrofit.RetroFit
import com.moviles.marketplace.api.retrofit.RetroFitService
import com.moviles.marketplace.models.MenssageModel

class ChatRepository {
    private val retrofitService: RetroFitService

    init {
        retrofitService = RetroFit.getRetrofitService()
    }

    //@GET("/api/chats/")
    fun getChat(listener: GetChatListener) {
        retrofitService.getChat().enqueue(object : retrofit2.Callback<Chat> {
            override fun onFailure(call: retrofit2.Call<Chat>, t: Throwable) {
                listener.onGetChatError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Chat>,
                response: retrofit2.Response<Chat>
            ) {
                listener.getChatReady(response.body()!!)
            }
        })
    }

    //@GET("/api/chats/{id}")
    fun getChatById(id:Long, listener: GetChatByIdListener) {
        retrofitService.getChatById(id).enqueue(object : retrofit2.Callback<Chat> {
            override fun onFailure(call: retrofit2.Call<Chat>, t: Throwable) {
                listener.onGetChatByIdError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Chat>,
                response: retrofit2.Response<Chat>
            ) {
                listener.getChatByIdReady(response.body()!!)
            }
        })
    }

    //@GET("/api/chats/{id]/messages")
    fun getMessageForChat(id:Long, listener: GetMessageForChatListener) {
        retrofitService.getMessageForChat(id).enqueue(object : retrofit2.Callback<ArrayList<MenssageModel>> {
            override fun onFailure(call: retrofit2.Call<ArrayList<MenssageModel>>, t: Throwable) {
                listener.onGetMessageForChatError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<ArrayList<MenssageModel>>,
                response: retrofit2.Response<ArrayList<MenssageModel>>
            ) {
                listener.getMessageForChatReady(response.body()!!)
            }
        })
    }

    //@POST("/api/chats")
    fun createChat(chat: Chat, listener: CreateChatListener) {
        retrofitService.createChat(chat).enqueue(object : retrofit2.Callback<Chat> {
            override fun onFailure(call: retrofit2.Call<Chat>, t: Throwable) {
                listener.oCreateChatError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Chat>,
                response: retrofit2.Response<Chat>
            ) {
                listener.createChatReady(response.body()!!)
            }
        })
    }

    fun addMenssageWithChat(menssage: MenssageModel, listener: AddMenssageWithChatListener) {
        retrofitService.addMenssageWithChat(menssage).enqueue(object : retrofit2.Callback<MenssageModel> {
            override fun onFailure(call: retrofit2.Call<MenssageModel>, t: Throwable) {
                listener.oAddMenssageWithChatError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<MenssageModel>,
                response: retrofit2.Response<MenssageModel>
            ) {
                listener.addMenssageWithChatReady(response.body()!!)
            }
        })
    }

    fun addLocationWithChat(menssage: MenssageModel, listener: AddLocationWithChatListener) {
        retrofitService.addMenssageWithChat(menssage).enqueue(object : retrofit2.Callback<MenssageModel> {
            override fun onFailure(call: retrofit2.Call<MenssageModel>, t: Throwable) {
                listener.oAddLocationWithChatError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<MenssageModel>,
                response: retrofit2.Response<MenssageModel>
            ) {
                listener.addLocationWithChatReady(response.body()!!)
            }
        })
    }

    interface GetChatListener {
        fun getChatReady(chat: Chat)
        fun onGetChatError(t: Throwable)
    }

    interface GetChatByIdListener {
        fun getChatByIdReady(chat: Chat)
        fun onGetChatByIdError(t: Throwable)
    }

    interface GetMessageForChatListener {
        fun getMessageForChatReady(menssage: ArrayList<MenssageModel>)
        fun onGetMessageForChatError(t: Throwable)
    }

    interface CreateChatListener {
        fun createChatReady(chat: Chat)
        fun oCreateChatError(t: Throwable)
    }

    interface AddMenssageWithChatListener {
        fun addMenssageWithChatReady(menssage: MenssageModel)
        fun oAddMenssageWithChatError(t: Throwable)
    }

    interface AddLocationWithChatListener {
        fun addLocationWithChatReady(menssage: MenssageModel)
        fun oAddLocationWithChatError(t: Throwable)
    }


}