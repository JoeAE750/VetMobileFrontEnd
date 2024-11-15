package com.fisi.vetmobile.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fisi.vetmobile.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Dog
import compose.icons.fontawesomeicons.solid.ShoppingCart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VetMobileTopBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    title: String
) {
    TopAppBar(colors = topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary,
    ), navigationIcon = {
        if (canNavigateBack) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Retroceder"
                )
            }
        }
    }, title = {
        Text(
            title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset(x = 20.dp)
        )
    })
}

@Composable
fun VetMobileBottomBar() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Mascotas", "Citas", "Productos", "Opciones")
    val selectedIcons = listOf(FontAwesomeIcons.Solid.Dog, Icons.Filled.DateRange, FontAwesomeIcons.Solid.ShoppingCart, Icons.Filled.Menu)
    val unselectedIcons =
        listOf(FontAwesomeIcons.Solid.Dog, Icons.Outlined.DateRange, FontAwesomeIcons.Solid.ShoppingCart, Icons.Outlined.Menu)

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                        contentDescription = item,
                        modifier = Modifier.size(18.dp)
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "Cargando"
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "Failed to load", modifier = Modifier.padding(16.dp))
    }
}


sealed interface ConexionUIState {
    object Success : ConexionUIState
    object Error : ConexionUIState
    object Loading : ConexionUIState
}