package com.moviles.marketplace.models

import java.io.Serializable

data class Response(
    val message: String,
    val res: Boolean
): Serializable
