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
import androidx.compose.material3.Scaffold
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
    navController: NavHostController = rememberNavController()
) {
    Scaffold(){

    }
    NavHost(
        navController = navController,
        startDestination = VetMobileScreen.Welcome.name
    ) {
        composable(route = VetMobileScreen.Login.name) {
            val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
            LoginScreen(loginViewModel) {
                navController.navigate(VetMobileScreen.Mascotas.name)
            }
        }
        composable(route = VetMobileScreen.Mascotas.name) {
            MascotasScreen()
        }
        composable(route = "register_mascota") {
            RegistrarMascotaScreen { mascota: Mascotas ->
                navController.popBackStack()
            }
        }
    }
}

