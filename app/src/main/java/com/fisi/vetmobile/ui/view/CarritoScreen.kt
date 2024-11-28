package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fisi.vetmobile.R
import com.fisi.vetmobile.data.model.Productos
import com.fisi.vetmobile.ui.viewmodel.CarritoViewModel

@Composable
fun CarritoScreen(
    carritoViewModel: CarritoViewModel = viewModel()
) {
    val productosEnCarrito = carritoViewModel.productosEnCarrito
    val total = carritoViewModel.calcularTotal()

    // Imagen de fondo
    val backgroundImage = painterResource(id = R.drawable.fondo1)

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Imagen de fondo
        Image(
            painter = backgroundImage,
            contentDescription = "Imagen de fondo 1",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomCenter)
        )

        // Lista de productos en el carrito
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(productosEnCarrito) { producto ->
                ProductoCardCarrito(
                    producto = producto,
                    onEliminarProductoClick = { carritoViewModel.removeProducto(producto) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // Total del carrito
        Text(
            text = "Total: $${total}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
        )

        // Botón flotante para añadir productos
        FloatingActionButton(
            onClick = { carritoViewModel.addProducto(Productos(id_producto = "1", id_categoria = "1", nombre = "Producto Ejemplo", descripcion = "Descripción del producto", precio = "10.0", stock = "5")) },
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Añadir Producto")
        }
    }
}

@Composable
fun ProductoCardCarrito(producto: Productos, onEliminarProductoClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF263238)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = producto.nombre,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f) // Asegura que el texto ocupe el espacio restante
            )

            IconButton(onClick = { onEliminarProductoClick() }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Eliminar Producto",
                    tint = Color.Red
                )
            }
        }
    }
}
