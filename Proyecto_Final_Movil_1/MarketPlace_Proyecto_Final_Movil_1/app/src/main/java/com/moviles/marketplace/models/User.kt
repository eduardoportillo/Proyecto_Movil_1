package com.moviles.marketplace.models

import java.io.Serializable

data class User(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val notification_id: String? = null
): Serializable

