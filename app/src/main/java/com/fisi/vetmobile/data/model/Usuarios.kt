package com.fisi.vetmobile.data.model

data class Usuarios(
    val id: Int? = null,
    val username: String,
    val password_hash: String,
    val nombre: String,
    val apellido: String,
    val email: String,
    val celular: String,
    val fechareg: String? = null
)