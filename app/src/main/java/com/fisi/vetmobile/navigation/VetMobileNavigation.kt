/*
package com.fisi.vetmobile.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fisi.vetmobile.ui.view.HomeScreen
import com.fisi.vetmobile.ui.view.LoginScreen
import com.fisi.vetmobile.ui.view.MascotasScreen
import com.fisi.vetmobile.ui.viewmodel.LoginViewModel

@Composable
fun VetMobileApp(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel = viewModel()
) {

    NavHost(
        navController = navController,
        startDestination = VetMobileScreen.Home.name,
    ) {
        composable(route = VetMobileScreen.Home.name) {
            HomeScreen(onLoginClick = { navController.navigate(VetMobileScreen.Login.name) })
        }
        composable(route = VetMobileScreen.Login.name) {
            LoginScreen(loginViewModel)
        }
        composable(route = VetMobileScreen.Mascotas.name) {
            MascotasScreen()
        }
    }
}
*/

package com.fisi.vetmobile.navigation
import com.fisi.vetmobile.data.model.Mascotas
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fisi.vetmobile.ui.view.LoginScreen
import com.fisi.vetmobile.ui.view.MascotasScreen
import com.fisi.vetmobile.ui.view.RegistrarMascotaScreen
import com.fisi.vetmobile.ui.viewmodel.LoginViewModel


@Composable
fun VetMobileApp(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = VetMobileScreen.Login.name
    ) {
        composable(route = VetMobileScreen.Login.name) {
            LoginScreen(loginViewModel) {
                navController.navigate(VetMobileScreen.Mascotas.name)
            }
        }
        composable(route = VetMobileScreen.Mascotas.name) {
            MascotasScreen(
                onRegisterMascotaClick = { navController.navigate("register_mascota") }
            )
        }
        composable(route = "register_mascota") {
            RegistrarMascotaScreen { mascota: Mascotas ->  // Especifica el tipo aquí
                // Aquí podrías manejar el registro de la mascota, enviarla al servidor o almacenarla localmente
                navController.popBackStack() // Vuelve a la pantalla anterior después de registrar
            }
        }
    }
}

