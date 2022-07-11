package com.moviles.marketplace.api.retrofit

import com.example.marketplace.models.Chat
import com.example.marketplace.models.Search
import com.moviles.marketplace.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface RetroFitService {
    //Auth
    @POST("/api/userregister")
    fun registerUser(@Body User: User): Call<User>

    @POST("/api/userlogin")
    fun loginUser(@Body User: User): Call<User>

    @GET("/api/me")
    fun getUser(): Call<User>

    // TODO introducir despues update notification_id

    //Products
    @GET("/api/products")
    fun getProductUserLoged(): Call<ArrayList<Product>>

    @POST("/api/products")
    fun createProduct(@Body product: Product): Call<Product>

    @PUT("/api/products/{id}")
    fun updateProduct(@Body product: Product, @Path("id") id: Long): Call<Product>

    @DELETE("/api/products/{id}")
    fun deleteProduct(@Path("id") id: Long): Call<Response>

    @GET("/api/products/{id}")
    fun getProductById(@Path("id") id: Long): Call<Product>

    @POST("/api/products/search")
    fun getAllProductsWithSearch(@Body search: Search): Call<ArrayList<Product>>

//    fun addProductPhoto(@Path("id") id: Long, @Part image: MultipartBody.Part): Call<*>
    @Multipart
    @POST("/api/products/{id}/images")
    fun addProductPhoto(
        @Path("id") id: Long,
        @Part multipartImage: MultipartBody.Part?,
    ): Call<RequestBody>

    @DELETE("/api/images/{id}")
    fun deletePhoto(@Path("id") id: Long): Call<Response>

    //Categories
    @GET("/api/categories")
    fun getCategories(): Call<ArrayList<Category>>

    @GET("/api/categories/{id}/products")
    fun getProductByCategories(@Path("id") id: Long): Call<Product>

    @POST("/api/categories")
    fun createCategory(@Body category: Category): Call<Category>

    @PUT("/api/categories/{id}")
    fun updateCategory(@Path("id") id: Long, @Body category: Category): Call<Category>

    @DELETE("/api/categories/{id}")
    fun deleteCategory(@Path("id") id: Long): Call<Category>

    //Chats
    @GET("/api/chats/")
    fun getChat(): Call<ArrayList<Chat>>

    @GET("/api/chats/{id}")
    fun getChatById(@Path("id") id: Long): Call<Chat>

    @GET("/api/chats/{id}/messages")
    fun getMessageForChat(@Path("id") id: Long): Call<ArrayList<MenssageModel>>

    @POST("/api/chats")
    fun createChat(@Body chat: Chat): Call<Chat>

    @POST("/api/messages")
    fun addMenssageWithChat(@Body message: MenssageModel): Call<MenssageModel>

    @POST("/api/messages")
    fun addLocationWithChat(@Body message: MenssageModel): Call<MenssageModel>

    @Multipart //TODO ver como implementar al final con Programación Android by AristiDevs y en ChatRepository
    @POST("/api/messages")
    fun addPhotoWithChat(@Body message: MenssageModel): Call<MenssageModel>
}