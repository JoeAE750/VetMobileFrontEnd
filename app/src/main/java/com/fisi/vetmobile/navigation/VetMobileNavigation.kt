package com.fisi.vetmobile.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fisi.vetmobile.R
import com.fisi.vetmobile.ui.view.LoginScreen
import com.fisi.vetmobile.ui.view.MascotasScreen
import com.fisi.vetmobile.ui.view.RegistrarMascotaScreen
import com.fisi.vetmobile.ui.view.RegistroUsuarioScreen
import com.fisi.vetmobile.ui.view.WelcomeScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VetMobileApp(
    navController: NavHostController = rememberNavController(),
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = VetMobileScreen.valueOf(
        backStackEntry?.destination?.route ?: VetMobileScreen.Welcome.name
    )

    Scaffold(
        topBar = {},
        bottomBar = {}
    ){ innerPadding ->

        NavHost(
            navController = navController,
            startDestination = VetMobileScreen.Welcome.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable( route = VetMobileScreen.Welcome.name){
                WelcomeScreen(onLoginClick = {
                    navController.navigate(VetMobileScreen.Login.name)
                }, onRegisterClick = {
                    navController.navigate(VetMobileScreen.RegistroUsuario.name)
                })
            }
            composable( route = VetMobileScreen.Login.name){
                LoginScreen(onLoginSuccess = {navController.navigate(VetMobileScreen.Mascotas.name)}, navigateUp = {navController.navigateUp()})
            }
            composable( route = VetMobileScreen.RegistroUsuario.name){
                RegistroUsuarioScreen(navigateUp = {navController.navigateUp()}, onRegisterSuccess = {navController.navigate(VetMobileScreen.Mascotas.name)})
            }
            composable( route = VetMobileScreen.Mascotas.name){
                MascotasScreen()
            }
            composable( route = VetMobileScreen.RegistroMascota.name){
                RegistrarMascotaScreen()
            }
        }

    }

}

