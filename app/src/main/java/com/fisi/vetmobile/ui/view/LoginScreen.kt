package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fisi.vetmobile.R
import com.fisi.vetmobile.ui.components.ConexionUIState
import com.fisi.vetmobile.ui.components.ErrorScreen
import com.fisi.vetmobile.ui.components.LoadingScreen
import com.fisi.vetmobile.ui.components.VetMobileBottomBar
import com.fisi.vetmobile.ui.components.VetMobileTopBar
import com.fisi.vetmobile.ui.theme.VetMobileTheme
import com.fisi.vetmobile.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    loginUIConexion: ConexionUIState = ConexionUIState.Success
) {
    val loginUiState by loginViewModel.uiState.collectAsState()
    Scaffold(topBar = {
        VetMobileTopBar(canNavigateBack = false, title = "Login")
    }, bottomBar = {
        VetMobileBottomBar()
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (loginUIConexion) {
                is ConexionUIState.Success -> {

                }

                is ConexionUIState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
                is ConexionUIState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            }
            val imagePainter = painterResource(id = R.drawable.vet_launcher_foreground)
            Image(
                painter = imagePainter,
                contentDescription = "Icono VetMobile",
                modifier = Modifier.size(100.dp)
            )

            Row {
                Text(text = "username", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                TextField(
                    value = loginViewModel.username,
                    onValueChange = { loginViewModel.username = it },
                    trailingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = "Username") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(onDone = { }),
                    singleLine = true
                )
            }



            Row {
                Text(text = "Contraseña", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                TextField(
                    value = loginViewModel.contrasena,
                    onValueChange = { loginViewModel.contrasena = it },
                    trailingIcon = { Icon(Icons.Default.Lock, contentDescription = "Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    keyboardActions = KeyboardActions(onDone = { }),
                    singleLine = true
                )
            }

            Button(onClick = {
                loginViewModel.validarLogin(
                    username = loginViewModel.username,
                    contrasena = loginViewModel.contrasena
                )
            }
            )
            { Text(text = "Iniciar Sesion", fontSize = 11.sp, fontWeight = FontWeight.Bold) }


            Text(
                text = loginUiState.isLoginSuccesfull.toString(),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@Preview
@Composable
fun LoginScreenPreview() {
    VetMobileTheme(darkTheme = false) {
        LoginScreen()
    }
}