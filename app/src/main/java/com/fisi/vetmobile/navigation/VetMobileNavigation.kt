package com.fisi.vetmobile.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fisi.vetmobile.ui.view.HomeScreen
import com.fisi.vetmobile.ui.view.LoginScreen
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
    }
}