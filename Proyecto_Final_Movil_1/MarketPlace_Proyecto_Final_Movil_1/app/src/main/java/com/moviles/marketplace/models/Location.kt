package com.moviles.marketplace.models

import java.io.Serializable

data class Location(
    val latitude: String? = null,
    val longitude: String? = null
): Serializable
