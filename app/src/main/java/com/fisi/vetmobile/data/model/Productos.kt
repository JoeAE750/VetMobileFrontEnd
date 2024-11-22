package com.fisi.vetmobile.data.model


data class Productos(
    val id_producto: String? = null,
    val id_categoria: String,
    val nombre:String,
    val descripcion: String,
    val precio: String,
    val stock: String
)
