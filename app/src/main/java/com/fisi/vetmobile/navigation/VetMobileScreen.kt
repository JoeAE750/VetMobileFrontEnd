package com.fisi.vetmobile.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.fisi.vetmobile.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Dog
import compose.icons.fontawesomeicons.solid.ShoppingCart

enum class VetMobileScreen(@StringRes val vista: Int,val icon: ImageVector? = null) {
        Welcome(vista = R.string.app_name),
        Login(vista = R.string.login),
        RegistroUsuario(vista = R.string.registrousuario),
        Mascotas(vista = R.string.mascotas, icon = FontAwesomeIcons.Solid.Dog),
        RegistroMascota(vista = R.string.registromascota),
        Opciones(vista = R.string.options, icon = Icons.Filled.Menu),
        Productos(vista = R.string.productos, icon = FontAwesomeIcons.Solid.ShoppingCart),
        Citas(vista = R.string.citas, icon = Icons.Filled.DateRange),
        Carrito(vista = R.string.carrito)
}