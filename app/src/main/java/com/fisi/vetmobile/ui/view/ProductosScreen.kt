
/*
@Preview
@Composable
fun PreviewCatalogo() {
    val products = listOf(
        Productos(
            nombre = "Producto 1",
            descripcion = "Descripción del producto 1",
            precio = 10.99),
        Productos(
            nombre = "Producto 2",
            descripcion = "Descripción del producto 2",
            precio = 80.99),
        Productos(
            nombre = "Producto 3",
            descripcion = "Descripción del producto 3",
            precio = 70.99),
        Productos(
            nombre = "Producto 1",
            descripcion = "Descripción del producto 4",
            precio = 10.99),
        Productos(
            nombre = "Producto 1",
            descripcion = "Descripción del producto 5",
            precio = 10.99),
        Productos(
            nombre = "Producto 1",
            descripcion = "Descripción del producto 6",
            precio = 10.99)
    )

    ProductCatalogue(
        modifier = Modifier,
        products = products,
        title = "Productos",
        onAddToCartClick = {},
        onNavigateToProductDetails = {}
    )
}
*/package com.fisi.vetmobile.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fisi.vetmobile.R
import com.fisi.vetmobile.data.model.Productos
import com.fisi.vetmobile.ui.viewmodel.CarritoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProductosScreen(
    onCarritoClick: () -> Unit,
    carritoViewModel: CarritoViewModel = viewModel(), // Aquí obtenemos el ViewModel
    modifier: Modifier = Modifier
) {
    Scaffold { paddingValues ->
        Box(modifier = modifier.padding(paddingValues)) {
            Row(Modifier.fillMaxWidth()) {
                Image(
                    modifier = modifier
                        .padding(start = 175.dp)
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.vet_launcher_round),
                    contentDescription = null,
                )
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Carrito de Compras",
                    modifier = Modifier
                        .padding(top = 20.dp, start = 110.dp)
                        .clickable { onCarritoClick() })
            }

            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(top = 64.dp)
                    .clip(RoundedCornerShape(topStartPercent = 8, topEndPercent = 8))
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                Text(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    text = "Bienvenido a nuestra tienda"
                )

                // Lista de productos en diferentes categorías
                val categorias = listOf(
                    "Juguetes" to listOf(
                        Productos("1", "Juguete para perro", "Juguete para morder", "5.99", "10","5"),
                        Productos("1", "Pelota para perro", "Pelota de goma", "7.99", "12","5"),
                        Productos("1", "Hueso de caucho", "Hueso para morder", "9.99", "8","5"),
                        Productos("1", "Juguete interactivo", "Juguete para estimular a los perros", "11.99", "5","5")
                    ),
                    "Comida" to listOf(
                        Productos("2", "Croquetas para perro", "Comida balanceada", "20.99", "50","5"),
                        Productos("2", "Comida húmeda para perro", "Comida de lata", "15.99", "30","5"),
                        Productos("2", "Galletas para perro", "Galletas sabor pollo", "3.99", "100","5"),
                        Productos("2", "Huesos para perro", "Huesos comestibles", "8.99", "40","5")
                    ),
                    "Otros" to listOf(
                        Productos("3", "Collar para perro", "Collar ajustable", "12.99", "15","5"),
                        Productos("3", "Correa para perro", "Correa de nylon", "7.49", "25","5"),
                        Productos("3", "Bañera para perro", "Bañera plegable", "35.99", "10","5"),
                        Productos("3", "Shampoo para perro", "Shampoo suave para piel sensible", "6.99", "30","5")
                    )
                )

                // Mostrar productos por categorías
                categorias.forEach { (categoria, productos) ->
                    Text(text = categoria, style = MaterialTheme.typography.titleLarge)
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        items(productos) { producto ->
                            ProductoCardProducto(
                                producto = producto,
                                onAddToCartClick = {
                                    carritoViewModel.agregarAlCarrito(producto) // Agregar al carrito
                                }
                            )
                        }
                    }
                }

                // Botón para ver el carrito
                Button(
                    onClick = onCarritoClick,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Ver Carrito")
                }
            }
        }
    }
}


@Composable
fun CategoriaProductos(categoriaNombre: String, productos: List<Productos>) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = categoriaNombre,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        // Mostrar los productos de la categoría en un LazyRow
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
        ) {
            items(productos) { producto ->
                ProductoCardProducto(
                    producto = producto,
                    onAddToCartClick = { /* Manejar añadir al carrito */ }
                )
            }
        }
    }
}

@Composable
fun ProductoCardProducto(producto: Productos, onAddToCartClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(160.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            Text(text = producto.nombre, fontWeight = FontWeight.Bold)
            Text(text = "$${producto.precio}")
            Button(onClick = onAddToCartClick) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Añadir al carrito")
                Text(text = "Añadir")
            }
        }
    }
}

