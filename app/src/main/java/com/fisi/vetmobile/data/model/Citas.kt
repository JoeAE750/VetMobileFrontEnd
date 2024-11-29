package com.fisi.vetmobile.data.model

data class Citas(
    val id_cita: String? = null,
    val id_mascota: String,
    val id_veterinario: String,
    val fecha_cita: String, // Incluye la fecha y hora como String en formato adecuado
    val razon: String,
    val id_estado: String,
    val id_servicio: String,
    val id_usuario: String
)
