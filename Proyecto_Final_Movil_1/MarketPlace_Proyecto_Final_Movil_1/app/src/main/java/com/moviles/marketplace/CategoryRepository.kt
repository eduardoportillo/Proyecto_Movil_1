package com.moviles.marketplace

import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.api.retrofit.RetroFit
import com.moviles.marketplace.api.retrofit.RetroFitService
import com.moviles.marketplace.models.Category
import com.moviles.marketplace.models.Product
import retrofit2.http.GET

class CategoryRepository {
    private val retrofitService: RetroFitService

    init {
        retrofitService = RetroFit.getRetrofitService()
    }

    //@GET("/api/categories")
    fun getCategories(listener: GetCategoriesListener){
        retrofitService.getCategories().enqueue(object : retrofit2.Callback<ArrayList<Category>> {
            override fun onFailure(call: retrofit2.Call<ArrayList<Category>>, t: Throwable) {
                listener.onGetCategoriesError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<ArrayList<Category>>,
                response: retrofit2.Response<ArrayList<Category>>
            ) {
                listener.getCategoriesReady(response.body()!!)
            }
        })
    }

    //@GET("/api/categories/{id}/products")
    fun getProductByCategories(id:Long, listener: GetProductByCategoriesListener) {
        retrofitService.getProductByCategories(id).enqueue(object : retrofit2.Callback<Product> {
            override fun onFailure(call: retrofit2.Call<Product>, t: Throwable) {
                listener.onGetProductByCategoriesError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Product>,
                response: retrofit2.Response<Product>
            ) {
                listener.getProductByCategoriesReady(response.body()!!)
            }
        })
    }

    //@POST("/api/categories")
    fun createCategory(category: Category, listener: CreateCategoryListener) {
        retrofitService.createCategory(category).enqueue(object : retrofit2.Callback<Category> {
            override fun onFailure(call: retrofit2.Call<Category>, t: Throwable) {
                listener.onCreateCategoryError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Category>,
                response: retrofit2.Response<Category>
            ) {
                listener.createCategoryReady(response.body()!!)
            }
        })
    }

    //@PUT("/api/categories/{id}")
    fun updateCategory(id: Long, category: Category, listener: UpdateCategoryListener) {
        retrofitService.updateCategory(id, category).enqueue(object : retrofit2.Callback<Category> {
            override fun onFailure(call: retrofit2.Call<Category>, t: Throwable) {
                listener.onUpdateCategoryError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Category>,
                response: retrofit2.Response<Category>
            ) {
                listener.updateCategoryReady(response.body()!!)
            }
        })
    }

    //@DELETE("/api/categories/{id}")
    fun deleteCategory(id: Long, listener: DeleteCategoryListener) {
        retrofitService.deleteCategory(id).enqueue(object : retrofit2.Callback<Category> {
            override fun onFailure(call: retrofit2.Call<Category>, t: Throwable) {
                listener.onDeleteCategoryError(t)
            }
            override fun onResponse(
                call: retrofit2.Call<Category>,
                response: retrofit2.Response<Category>
            ) {
                listener.deleteCategoryReady(response.body()!!)
            }
        })
    }

    interface GetCategoriesListener {
        fun getCategoriesReady(categories: ArrayList<Category>)
        fun onGetCategoriesError(t: Throwable)
    }

    interface GetProductByCategoriesListener {
        fun getProductByCategoriesReady(products: Product)
        fun onGetProductByCategoriesError(t: Throwable)
    }

    interface CreateCategoryListener {
        fun createCategoryReady(category: Category)
        fun onCreateCategoryError(t: Throwable)
    }

    interface UpdateCategoryListener {
        fun updateCategoryReady(category: Category)
        fun onUpdateCategoryError(t: Throwable)
    }

    interface DeleteCategoryListener {
        fun deleteCategoryReady(category: Category)
        fun onDeleteCategoryError(t: Throwable)
    }
}