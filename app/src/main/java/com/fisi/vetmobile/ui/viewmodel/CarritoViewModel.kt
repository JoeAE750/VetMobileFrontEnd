package com.fisi.vetmobile.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fisi.vetmobile.data.model.Productos

class CarritoViewModel : ViewModel() {

    // Lista mutable para almacenar los productos en el carrito
    private val _productosEnCarrito = mutableStateOf<List<Productos>>(emptyList())
    val productosEnCarrito get() = _productosEnCarrito.value

    // Función para añadir productos al carrito
    fun addProducto(producto: Productos) {
        _productosEnCarrito.value = _productosEnCarrito.value + producto
    }

    // Función para eliminar productos del carrito
    fun removeProducto(producto: Productos) {
        _productosEnCarrito.value = _productosEnCarrito.value.filter { it.id_producto != producto.id_producto }
    }

    // Función para calcular el total del carrito
    fun calcularTotal(): Double {
        return _productosEnCarrito.value.sumOf { it.precio.toDouble() }
    }
}
