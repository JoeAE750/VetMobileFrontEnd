package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fisi.vetmobile.data.model.Usuarios
import com.fisi.vetmobile.ui.components.Boton_Atras
import com.fisi.vetmobile.ui.components.TextFieldFormulario
import com.fisi.vetmobile.ui.viewmodel.LoginViewModel

@Composable
fun RegistroUsuarioScreen(
    navigateUp: () -> Unit,
    loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
    onRegisterSuccess: () -> Unit,
) {

    val loginUiState by loginViewModel.uiState.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background), // Dark background
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(loginUiState.isRegisterSuccesfull){
            onRegisterSuccess()
        }

        Boton_Atras(modifier = Modifier.padding(16.dp).align(Alignment.Start), navigateUp = navigateUp)

        Icon(
            imageVector = Icons.Filled.AccountBox, // Replace with your registration icon resource
            contentDescription = "Registro Icono",
            modifier = Modifier
                .size(80.dp)
                .padding(top = 10.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        // Title
        Text(
            text = "REGISTRO",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default // Replace with a custom font if needed
            ),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Username Text Field
        TextFieldFormulario(value = username, onValueChange = { username = it }, label = "Nombre de Usuario")
        TextFieldFormulario(value = password, onValueChange = { password = it }, label = "Contraseña", isPassword = true)
        TextFieldFormulario(value = firstName, onValueChange = { firstName = it }, label = "Nombre")
        TextFieldFormulario(value = lastName, onValueChange = { lastName = it }, label = "Apellido")
        TextFieldFormulario(value = email, onValueChange = { email = it }, label = "Direccion de Correo Electronico")
        TextFieldFormulario(value = phone, onValueChange = { phone = it }, label = "Celular")

        Spacer(modifier = Modifier.height(16.dp))

        val newUsuario = Usuarios(username = username, password_hash = password, nombre = firstName, apellido = lastName, email = email, celular =  phone)

        Button(
            onClick = { loginViewModel.registrarUsuario(newUsuario)},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(0.6f)
        ) {
            Text(text = "Registrar", color = Color.White)
        }
    }
}

/*
@Preview(apiLevel = 34)
@Composable
fun PreviewRegistroUsuarioScreen() {
    RegistroUsuarioScreen( navigateUp = true)
}

 */