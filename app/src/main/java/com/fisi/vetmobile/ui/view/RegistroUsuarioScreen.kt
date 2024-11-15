package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegistrationScreen() {
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
        // Back Icon
        IconButton(
            onClick = { /* Handle back navigation */ },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft, // Replace with your back icon resource
                contentDescription = "Back"
            )
        }

        // Registration Icon
        Icon(
            imageVector = Icons.Filled.AccountBox, // Replace with your registration icon resource
            contentDescription = "Register Icon",
            modifier = Modifier
                .size(80.dp)
                .padding(top = 16.dp),
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

        Spacer(modifier = Modifier.height(16.dp))

        // Username Text Field
        CustomTextField(value = username, onValueChange = { username = it }, label = "Nombre de Usuario")
        CustomTextField(value = password, onValueChange = { password = it }, label = "Contraseña", isPassword = true)
        CustomTextField(value = firstName, onValueChange = { firstName = it }, label = "Nombre")
        CustomTextField(value = lastName, onValueChange = { lastName = it }, label = "Apellido")
        CustomTextField(value = email, onValueChange = { email = it }, label = "Direccion de Correo Electronico")
        CustomTextField(value = phone, onValueChange = { phone = it }, label = "Celular")

        Spacer(modifier = Modifier.height(16.dp))

        // Register Button
        Button(
            onClick = { /* Handle register action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.6f)
        ) {
            Text(text = "Registrar", color = Color.White)
        }
    }
}


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 30.dp)) {
        Text(
            text = label,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.padding(start = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.colors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = MaterialTheme.shapes.small
        )
    }
}


@Preview
@Composable
fun PreviewRegistrationScreen() {
    RegistrationScreen()
}