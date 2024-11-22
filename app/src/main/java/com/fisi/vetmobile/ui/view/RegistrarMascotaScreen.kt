package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fisi.vetmobile.data.model.Mascotas // Importa la clase Mascotas
import com.fisi.vetmobile.ui.components.Boton_Atras
import com.fisi.vetmobile.ui.components.TextFieldFormulario
import com.fisi.vetmobile.ui.viewmodel.LoginViewModel
import com.fisi.vetmobile.ui.viewmodel.MascotasViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Paw

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarMascotaScreen(
    navigateUp: () -> Unit,
    mascotasViewModel: MascotasViewModel = viewModel(factory = MascotasViewModel.Factory),
    idusuario: String
) {
    var nombre by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Boton_Atras(modifier = Modifier.padding(16.dp).align(Alignment.Start), navigateUp = navigateUp)

        Icon(
            imageVector = FontAwesomeIcons.Solid.Paw, // Replace with your registration icon resource
            contentDescription = "Registro Mascota Icono",
            modifier = Modifier
                .size(80.dp)
                .padding(top = 10.dp),
            tint = MaterialTheme.colorScheme.primary
        )


        Text(
            text = "REGISTRO DE MASCOTA",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default // Replace with a custom font if needed
            ),
            modifier = Modifier.padding(8.dp)
        )

        TextFieldFormulario(value = nombre, onValueChange = { nombre = it }, label = "Nombre")

        val options = listOf("Perro", "Gato")
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Label") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = {},
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

        TextFieldFormulario(value = raza, onValueChange = { raza = it }, label = "Raza")
        TextFieldFormulario(value = edad, onValueChange = { edad = it }, label = "Edad")
        TextFieldFormulario(value = peso, onValueChange = { peso = it }, label = "Peso")
        TextFieldFormulario(value = genero, onValueChange = { genero = it }, label = "Genero")


        val nuevaMascota = Mascotas(
            id_usuario = idusuario.toInt(),
            nombre = nombre,
            especie = if(selectedOptionText == "Perro") "1" else "2",
            raza = raza,
            edad = edad,
            peso = peso,
            genero = genero
        )

        Button(onClick = {
            mascotasViewModel.registrarMascota(nuevaMascota)
        }) {
            Text("Registrar Mascota")
        }
    }
}

@Preview
@Composable
fun RegistrarMascotaScreenPreview() {
    //RegistrarMascotaScreen()
}