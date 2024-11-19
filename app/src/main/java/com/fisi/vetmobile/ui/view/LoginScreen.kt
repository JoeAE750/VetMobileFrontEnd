package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fisi.vetmobile.R
import com.fisi.vetmobile.ui.components.Boton_Atras
import com.fisi.vetmobile.ui.components.ConexionUIState
import com.fisi.vetmobile.ui.components.ErrorScreen
import com.fisi.vetmobile.ui.components.LoadingScreen
import com.fisi.vetmobile.ui.components.TextFieldFormulario
import com.fisi.vetmobile.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
    onLoginSuccess: () -> Unit,
    navigateUp: () -> Unit
) {
    val loginUiState by loginViewModel.uiState.collectAsState()
    val loginUIConexion = loginViewModel.loginUIConexion // Observa el estado de conexión

    Scaffold(topBar = {
    }, bottomBar = {}) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Boton_Atras(modifier = Modifier.padding(16.dp).align(Alignment.Start), navigateUp = navigateUp)

            when (loginUIConexion) {
                is ConexionUIState.Success -> {
                    if (loginUiState.isLoginSuccesfull) {
                        onLoginSuccess()
                    } else {
                        LoginForm(loginViewModel)
                    }
                }
                is ConexionUIState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
                is ConexionUIState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun LoginForm(loginViewModel: LoginViewModel) {
    val imagePainter = painterResource(id = R.drawable.vet_launcher_foreground)
    Image(
        painter = imagePainter,
        contentDescription = "Icono VetMobile",
        modifier = Modifier.size(100.dp)
    )

    TextFieldFormulario(
        value = loginViewModel.username,
        onValueChange = { loginViewModel.username = it },
        label = "Nombre de Usuario"
    )
    TextFieldFormulario(
        value = loginViewModel.contrasena,
        onValueChange = { loginViewModel.contrasena = it },
        label = "Contraseña",
        isPassword = true
    )


    Button(onClick = {
        loginViewModel.validarLogin(
            username = loginViewModel.username, contrasena = loginViewModel.contrasena
        )
    }) {
        Text(text = "Iniciar Sesión", fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
}

/*
@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginSuccess = {})
}
*/