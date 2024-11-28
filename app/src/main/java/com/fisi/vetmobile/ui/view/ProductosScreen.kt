
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
                        .clickable {})
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
                    text = stringResource(id = R.string.welcome_prompt)
                )

                val products = listOf(
                    Productos(
                        id_categoria = "1",
                        nombre = "Producto 1",
                        descripcion = "Descripción del producto 1",
                        precio = "10.99",
                        stock = "2"
                    ),
                    Productos(
                        id_categoria = "1",
                        nombre = "Producto 2",
                        descripcion = "Descripción del producto 2",
                        precio = "15.99",
                        stock = "4"
                    ),
                    Productos(
                        id_categoria = "1",
                        nombre = "Producto 3",
                        descripcion = "Descripción del producto 3",
                        precio = "12.99",
                        stock = "5"
                    )
                )

                // Lista de productos
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    items(products) { producto ->
                        ProductoCardProducto(
                            producto = producto,
                            onAddToCartClick = { /* Handle add to cart */ }
                        )
                    }
                }
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
