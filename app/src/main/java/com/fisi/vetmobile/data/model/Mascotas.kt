package com.fisi.vetmobile.data.model

data class Mascotas(
    val idmascota: String?= null,
    val idusuario: String,
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: String,
    val peso: String,
    val genero: String
)
