package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fisi.vetmobile.R
import com.fisi.vetmobile.data.model.Productos

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
                        nombre = "Producto 1",
                        descripcion = "Descripción del producto 1",
                        precio = "10.99",
                        stock = "2"
                    ),
                    Productos(
                        id_categoria = "1",
                        nombre = "Producto 1",
                        descripcion = "Descripción del producto 1",
                        precio = "10.99",
                        stock = "2"
                    ),
                    Productos(
                        id_categoria = "1",
                        nombre = "Producto 1",
                        descripcion = "Descripción del producto 1",
                        precio = "10.99",
                        stock = "2"
                    ),
                    Productos(
                        id_categoria = "1",
                        nombre = "Producto 1",
                        descripcion = "Descripción del producto 1",
                        precio = "10.99",
                        stock = "2"
                    ),
                    Productos(
                        id_categoria = "1",
                        nombre = "Producto 1",
                        descripcion = "Descripción del producto 1",
                        precio = "10.99",
                        stock = "2"
                    ),
                )

                ProductCatalogue(modifier = Modifier,
                    products = products,
                    title = "Medicina",
                    onAddToCartClick = {},
                    onNavigateToProductDetails = {})
                Spacer(modifier = modifier.height(12.dp))
                ProductCatalogue(modifier = Modifier,
                    products = products,
                    title = "Comida",
                    onAddToCartClick = {},
                    onNavigateToProductDetails = {})
                Spacer(modifier = modifier.height(12.dp))
                ProductCatalogue(modifier = Modifier,
                    products = products,
                    title = "Higiene",
                    onAddToCartClick = {},
                    onNavigateToProductDetails = {})
                Spacer(modifier = modifier.height(12.dp))
                ProductCatalogue(modifier = Modifier,
                    products = products,
                    title = "General",
                    onAddToCartClick = {},
                    onNavigateToProductDetails = {})
            }
        }
    }
}


@Composable
fun ProductCatalogue(
    modifier: Modifier,
    products: List<Productos>,
    title: String,
    onAddToCartClick: (Productos) -> Unit,
    onNavigateToProductDetails: (Productos) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title, style = MaterialTheme.typography.titleMedium
            )
            Text(text = "Mostrar Todo", modifier = modifier.clickable {

            })
        }

        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(products) { item ->
                ProductItem(modifier = modifier.padding(4.dp),
                    producto = item,
                    onAddToCartClick = onAddToCartClick,
                    onNavigateToProductDetails = { onNavigateToProductDetails(item) })
            }
        }
    }
}

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    producto: Productos,
    onAddToCartClick: (Productos) -> Unit,
    onNavigateToProductDetails: (String) -> Unit
) {
    Card(
        modifier = modifier
            .width(180.dp)
            .height(260.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp),
        ) {

            Text(
                modifier = modifier.fillMaxWidth(),
                text = producto.nombre,
                maxLines = 1,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                modifier = modifier.fillMaxWidth(),
                text = producto.descripcion,
                maxLines = 2,
            )
            Spacer(modifier = modifier.height(4.dp))
            Row(
                modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${producto.precio}$", style = MaterialTheme.typography.labelMedium
                )
                Button(
                    onClick = { onAddToCartClick(producto) },
                    modifier = Modifier.size(48.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

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
*/