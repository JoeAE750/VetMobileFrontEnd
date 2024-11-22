package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {

        Boton_Atras(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp),
            navigateUp = navigateUp
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val imagePainter = painterResource(id = R.drawable.vet_launcher_foreground)
            Image(
                painter = imagePainter,
                contentDescription = "Icono VetMobile",
                modifier = Modifier
                    .size(200.dp)
            )

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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        TextFieldFormulario(
            value = username,
            onValueChange = { loginViewModel.updateUsername(it) },
            label = "Nombre de Usuario",
            modifier = Modifier.fillMaxWidth()
        )


        TextFieldFormulario(
            value = password,
            onValueChange = { loginViewModel.updateContrasena(it) },
            label = "Contraseña",
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            onClick = {
                loginViewModel.validarLogin(username = username, contrasena = password)
            },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Iniciar Sesión",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}




@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginSuccess = {}, navigateUp = {})
}
