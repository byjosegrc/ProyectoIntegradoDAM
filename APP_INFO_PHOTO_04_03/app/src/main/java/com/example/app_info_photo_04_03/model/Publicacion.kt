package com.example.app_info_photo_04_03.model


data class Publicacion(
    var fecha: Long? = System.currentTimeMillis(),
    var autor: String? = null,
    var contenido: String? = null
):java.io.Serializable