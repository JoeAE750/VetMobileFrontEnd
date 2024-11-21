package com.fisi.vetmobile.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fisi.vetmobile.ui.components.VetMobileBottomBar
import com.fisi.vetmobile.ui.view.CitasScreen
import com.fisi.vetmobile.ui.view.LoginScreen
import com.fisi.vetmobile.ui.view.MascotasScreen
import com.fisi.vetmobile.ui.view.ProductosScreen
import com.fisi.vetmobile.ui.view.RegistrarMascotaScreen
import com.fisi.vetmobile.ui.view.RegistroUsuarioScreen
import com.fisi.vetmobile.ui.view.WelcomeScreen
import com.fisi.vetmobile.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VetMobileApp(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = VetMobileScreen.valueOf(
        backStackEntry?.destination?.route ?: VetMobileScreen.Welcome.name
    )

    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    val loginUiState by loginViewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState, drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Menu", fontSize = 24.sp, color = Color.Blue)
                    HorizontalDivider(color = Color.Gray)
                    NavigationDrawerItem(label = { Text("Perfil") },
                        selected = false,
                        onClick = { /* Handle Home action */ })
                    NavigationDrawerItem(label = { Text("Cerrar Sesion") },
                        selected = false,
                        onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                            loginViewModel.cerrarSesion()
                        })
                }
            }
        }, gesturesEnabled = false
    ) {
        Scaffold(topBar = {
            if (currentScreen !in listOf(VetMobileScreen.Welcome, VetMobileScreen.Login)) {
                TopAppBar(title = { Text(currentScreen.name) },
                    navigationIcon = { /* Add Navigation Icon if needed */ })
            }
        }, bottomBar = {
            if (currentScreen in listOf(
                    VetMobileScreen.Mascotas,
                    VetMobileScreen.Productos,
                    VetMobileScreen.Citas
                )
            ) {
                VetMobileBottomBar(
                    navController = navController,
                    scope = scope,
                    drawerState = drawerState
                )
            }
        }) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = if (loginUiState.isLoginSuccesfull) VetMobileScreen.Mascotas.name else VetMobileScreen.Welcome.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = VetMobileScreen.Welcome.name) {
                    WelcomeScreen(onLoginClick = {
                        navController.navigate(VetMobileScreen.Login.name)
                    }, onRegisterClick = {
                        navController.navigate(VetMobileScreen.RegistroUsuario.name)
                    })
                }
                composable(route = VetMobileScreen.Login.name) {
                    LoginScreen(onLoginSuccess = { },
                        navigateUp = { navController.navigateUp() })
                }
                composable(route = VetMobileScreen.RegistroUsuario.name) {
                    RegistroUsuarioScreen(navigateUp = { navController.navigateUp() },
                        onRegisterSuccess = { navController.navigate(VetMobileScreen.Mascotas.name) })
                }
                composable(route = VetMobileScreen.Mascotas.name) {
                    MascotasScreen(idusuario =loginUiState.idusuario)
                }
                composable(route = VetMobileScreen.RegistroMascota.name) {
                    RegistrarMascotaScreen()
                }
                composable(route = VetMobileScreen.Productos.name) {
                    ProductosScreen()
                }
                composable(route = VetMobileScreen.Citas.name) {
                    CitasScreen()
                }
            }
        }
    }
}


