package com.fisi.vetmobile.data.model

data class Mascotas(
    val id_mascota: Int?= null,
    val id_usuario: Int,
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: String,
    val peso: String,
    val genero: String
)
