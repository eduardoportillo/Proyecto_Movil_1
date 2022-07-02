package com.moviles.marketplace.models

import java.io.Serializable

data class Products(
    val id: Long? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val title: String? = null,
    val price: String? = null,
    val description: String? = null,

    val userID: Long? = null,

    val categoryID: Long? = null,

    val photos: ArrayList<Photo>? = null, //todo verify if it is an arraylist or a list
    val category: Category? = null
): Serializable
