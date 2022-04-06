package com.example.pettinder.models

class Mascota(
    var id: Int,
    var nombre: String,
    var edad: String,
    val url_photo: String
){
    override fun toString(): String {
        return  "$id $nombre $edad $url_photo"
    }
}