package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
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
    carritoViewModel: CarritoViewModel = viewModel(),
    onRegresarClick: () -> Unit // Para regresar a la pantalla de productos
) {
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

                // Título de la pantalla
                Text(
                    text = "Carrito de Compras",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Lista de productos en el carrito
                val productosEnCarrito = carritoViewModel.productosEnCarrito.value
                if (productosEnCarrito.isEmpty()) {
                    Text("El carrito está vacío", style = MaterialTheme.typography.bodyMedium)
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(productosEnCarrito) { producto ->
                            ProductoEnCarritoCard(producto = producto, onEliminarClick = {
                                carritoViewModel.eliminarDelCarrito(producto)
                            })
                        }
                    }
                }

                // Mostrar el total
                Spacer(modifier = Modifier.weight(1f)) // Para empujar el total al fondo
                val total = carritoViewModel.calcularTotal()
                Text(
                    text = "Total: \$${"%.2f".format(total)}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Botón de regresar a la pantalla de productos
                Button(onClick = onRegresarClick) {
                    Text("Regresar")
                }
            }
        }
    }
}

@Composable
fun ProductoEnCarritoCard(producto: Productos, onEliminarClick: () -> Unit) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onEliminarClick) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Eliminar producto")
            }
        }
    }
}
