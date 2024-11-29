package com.fisi.vetmobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fisi.vetmobile.data.model.Productos
import kotlinx.coroutines.launch

class CarritoViewModel : ViewModel() {

    // Lista de productos en el carrito
    var productosEnCarrito = mutableStateOf<List<Productos>>(emptyList())

    // Función para agregar un producto al carrito
    fun agregarAlCarrito(producto: Productos) {
        val productosActualizados = productosEnCarrito.value.toMutableList().apply {
            add(producto)
        }
        productosEnCarrito.value = productosActualizados
        Log.d("CarritoViewModel", "Producto añadido: ${producto.nombre}, Total productos en carrito: ${productosEnCarrito.value.size}")
    }


    // Función para eliminar un producto del carrito
    fun eliminarDelCarrito(producto: Productos) {
        val productosActualizados = productosEnCarrito.value.toMutableList().apply {
            remove(producto)
        }
        productosEnCarrito.value = productosActualizados
    }

    // Función para calcular el total del carrito
    fun calcularTotal(): Double {
        return productosEnCarrito.value.sumOf { it.precio.toDouble() }
    }
}
