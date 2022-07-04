package com.moviles.marketplace.models

import java.io.Serializable

data class MenssageModel(
    val id: Long? = null,

    val chat_id: Long? = null,

    val user_id: Long? = null,

    val message: String? = null,

    val location: Location? = null,
    val latitude: String? = null,
    val longitude: String? = null,

    val created_at: String? = null,

    val updated_at: String? = null,

    val url: String? = null,

    val user: User? = null
): Serializable
