package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.fisi.vetmobile.ui.components.TextFieldFormulario
import com.fisi.vetmobile.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
    onLoginSuccess: () -> Unit,
    navigateUp: () -> Unit
) {
    val loginUiState by loginViewModel.uiState.collectAsState()

    Scaffold(topBar = {
    }, bottomBar = {}) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Boton_Atras(modifier = Modifier.padding(16.dp).align(Alignment.Start), navigateUp = navigateUp)


                    if (loginUiState.isLoginSuccesfull) {
                        onLoginSuccess()
                    } else {
                        LoginForm(loginViewModel)
                    }
                }
            }
        }

@Composable
fun LoginForm(loginViewModel: LoginViewModel) {

    val username by loginViewModel.username.observeAsState("")
    val password by loginViewModel.contrasena.observeAsState("")

    val imagePainter = painterResource(id = R.drawable.vet_launcher_foreground)

    Image(
        painter = imagePainter,
        contentDescription = "Icono VetMobile",
        modifier = Modifier.size(100.dp)
    )

    TextFieldFormulario(
        value = username,
        onValueChange = { loginViewModel.updateUsername(it) },
        label = "Nombre de Usuario"
    )
    TextFieldFormulario(
        value = password,
        onValueChange = { loginViewModel.updateContrasena(it) },
        label = "Contraseña",
        isPassword = true
    )


    Button(onClick = {
        loginViewModel.validarLogin(
            username = username, contrasena = password
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