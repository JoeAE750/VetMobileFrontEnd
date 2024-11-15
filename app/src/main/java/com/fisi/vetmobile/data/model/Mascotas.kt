package com.fisi.vetmobile.data.model
import com.fisi.vetmobile.data.model.Mascotas

data class Mascotas(
    val idmascota: String,
    val idusuario: String,
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: String,
    val peso: String,
    val genero: String
)
