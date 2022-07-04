package com.moviles.marketplace.models

import java.io.Serializable

data class Photo(
    val product_id: String? = null,

    val id: Long? = null,
    val url: String? = null
): Serializable
