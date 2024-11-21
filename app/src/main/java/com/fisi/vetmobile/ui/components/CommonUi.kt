package com.fisi.vetmobile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fisi.vetmobile.navigation.VetMobileScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Boton_Atras(
    modifier: Modifier, navigateUp: () -> Unit
) {
    IconButton(
        onClick = navigateUp, modifier = modifier
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, // Replace with your back icon resource
            contentDescription = "Back"
        )
    }
}


@Composable
fun VetMobileBottomBar(
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val items = listOf(
        VetMobileScreen.Mascotas,
        VetMobileScreen.Productos,
        VetMobileScreen.Citas,
        VetMobileScreen.Opciones
    )
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(icon = { screen.icon?.let { Icon(it, contentDescription = null) } },
                label = { Text(screen.name) },
                selected = currentRoute == screen.name,
                onClick = {
                    if (screen == VetMobileScreen.Opciones) {
                        scope.launch {
                            if (drawerState.isClosed) {
                                drawerState.open()
                            } else {
                                drawerState.close()
                            }
                        }
                    } else {
                        navController.navigate(screen.name) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


@Composable
fun TextFieldFormulario(
    value: String, onValueChange: (String) -> Unit, label: String, isPassword: Boolean = false
) {
    Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 20.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
            textStyle = TextStyle(
                fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.colors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = MaterialTheme.shapes.small,
            singleLine = true
        )
    }
}

