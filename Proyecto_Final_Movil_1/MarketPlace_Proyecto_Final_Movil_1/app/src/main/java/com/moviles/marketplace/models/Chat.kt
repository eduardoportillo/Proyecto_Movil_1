package com.example.marketplace.models

import com.moviles.marketplace.models.Product
import com.moviles.marketplace.models.User

data class Chat (
    val id: Long? = null,

    val buyer_id: Long? = null,

    val seller_id: Long? = null,

    val product_id: Long? = null,

    val created_at: String? = null,

    val updated_at: String? = null,

    val buyer: User? = null,
    val seller: User? = null,
    val product: Product? = null
)



