package com.fisi.vetmobile.network

data class MascotasRequest(
    val id_usuario: String,
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: String,
    val peso: String,
    val genero: String
)
