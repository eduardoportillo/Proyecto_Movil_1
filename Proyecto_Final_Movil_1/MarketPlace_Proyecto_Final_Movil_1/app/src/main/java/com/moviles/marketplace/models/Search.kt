package com.example.marketplace.models

import java.io.Serializable

data class Search (
    val latitude: String? = null,
    val longitude: String? = null,
    val radius: Long? = null,

    val category_id: Long? = null
):Serializable

