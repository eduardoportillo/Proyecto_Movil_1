package com.moviles.marketplace.api

import android.content.Context
import android.net.Uri
import com.example.marketplace.models.Chat
import com.moviles.marketplace.api.retrofit.RetroFit
import com.moviles.marketplace.api.retrofit.RetroFitService
import com.moviles.marketplace.models.Menssage
import com.moviles.marketplace.utilities.FileUtils
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ChatRepository {
    private val retrofitService: RetroFitService

    init {
        retrofitService = RetroFit.getRetrofitService()
    }

    //@GET("/api/chats/")
    fun getChat(listener: GetChatListener) {
        retrofitService.getChat().enqueue(object : retrofit2.Callback<ArrayList<Chat>> {
            override fun onFailure(call: retrofit2.Call<ArrayList<Chat>>, t: Throwable) {
                listener.onGetChatError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<ArrayList<Chat>>,
                response: retrofit2.Response<ArrayList<Chat>>
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
        retrofitService.getMessageForChat(id).enqueue(object : retrofit2.Callback<ArrayList<Menssage>> {
            override fun onFailure(call: retrofit2.Call<ArrayList<Menssage>>, t: Throwable) {
                listener.onGetMessageForChatError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<ArrayList<Menssage>>,
                response: retrofit2.Response<ArrayList<Menssage>>
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

    fun addMenssageWithChat(menssage: Menssage, listener: AddMenssageWithChatListener) {
        retrofitService.addMenssageWithChat(menssage).enqueue(object : retrofit2.Callback<Menssage> {
            override fun onFailure(call: retrofit2.Call<Menssage>, t: Throwable) {
                listener.oAddMenssageWithChatError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Menssage>,
                response: retrofit2.Response<Menssage>
            ) {
                listener.addMenssageWithChatReady(response.body()!!)
            }
        })
    }

    fun addPhotoWithChat(
        chat_id: Long,
        fileUri: Uri,
        context: Context,
        listener: AddPhotoWithChatListener
    ) {

        val file: File = FileUtils.getFile(context, fileUri) ?: return
        val fileBody: RequestBody =
            RequestBody.create(MediaType.parse(context.contentResolver.getType(fileUri)), file)
        val body = MultipartBody.Part.createFormData("image", file.name, fileBody)

        val requestBody = RequestBody.create(MediaType.parse("text/plain"), chat_id.toString())

        retrofitService.addPhotoWithChat(requestBody, body)
            .enqueue(object : retrofit2.Callback<Menssage> {
                override fun onFailure(call: retrofit2.Call<Menssage>, t: Throwable) {
                    listener.oAddPhotonWithChatError(t)
                }

                override fun onResponse(
                    call: retrofit2.Call<Menssage>,
                    response: retrofit2.Response<Menssage>
                ) {
                    listener.addPhotoWithChatReady(response.body()!!)
                }
            })
    }

    fun addLocationWithChat(menssage: Menssage, listener: AddLocationWithChatListener) {
        retrofitService.addMenssageWithChat(menssage).enqueue(object : retrofit2.Callback<Menssage> {
            override fun onFailure(call: retrofit2.Call<Menssage>, t: Throwable) {
                listener.oAddLocationWithChatError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Menssage>,
                response: retrofit2.Response<Menssage>
            ) {
                listener.addLocationWithChatReady(response.body()!!)
            }
        })
    }

    interface GetChatListener {
        fun getChatReady(chats: ArrayList<Chat>)
        fun onGetChatError(t: Throwable)
    }

    interface GetChatByIdListener {
        fun getChatByIdReady(chat: Chat)
        fun onGetChatByIdError(t: Throwable)
    }

    interface GetMessageForChatListener {
        fun getMessageForChatReady(menssage: ArrayList<Menssage>)
        fun onGetMessageForChatError(t: Throwable)
    }

    interface CreateChatListener {
        fun createChatReady(chat: Chat)
        fun oCreateChatError(t: Throwable)
    }

    interface AddMenssageWithChatListener {
        fun addMenssageWithChatReady(menssage: Menssage)
        fun oAddMenssageWithChatError(t: Throwable)
    }

    interface AddLocationWithChatListener {
        fun addLocationWithChatReady(menssage: Menssage)
        fun oAddLocationWithChatError(t: Throwable)
    }

    interface AddPhotoWithChatListener {
        fun addPhotoWithChatReady(menssage: Menssage)
        fun oAddPhotonWithChatError(t: Throwable)
    }

}