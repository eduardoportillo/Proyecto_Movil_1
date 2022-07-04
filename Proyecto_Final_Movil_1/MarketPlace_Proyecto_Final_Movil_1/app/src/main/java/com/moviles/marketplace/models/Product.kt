package com.moviles.marketplace.models

import java.io.Serializable

data class Product(
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val price: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,

    val category_id: Long? = null,

    val user_id: Long? = null,

    val photos: ArrayList<Photo>? = null, //todo verify if it is an arraylist or a list
    val category: Category? = null,

    val distance: Long? = null,
): Serializable
