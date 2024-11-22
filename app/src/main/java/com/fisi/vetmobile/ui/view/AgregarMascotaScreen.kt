package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fisi.vetmobile.ui.viewmodel.AgregarMascotaViewModel
import com.fisi.vetmobile.ui.viewmodel.MascotasViewModel
@Composable
fun AgregarMascotaScreen(
    agregarMascotaViewModel: AgregarMascotaViewModel = viewModel() // Usamos el nuevo ViewModel
) {
    val nombre = remember { mutableStateOf("") }
    val especie = remember { mutableStateOf("") }
    val raza = remember { mutableStateOf("") }
    val edad = remember { mutableStateOf("") }
    val peso = remember { mutableStateOf("") }
    val genero = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Agregar Nueva Mascota")

        TextField(
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre") }
        )

        TextField(
            value = especie.value,
            onValueChange = { especie.value = it },
            label = { Text("Especie") }
        )

        TextField(
            value = raza.value,
            onValueChange = { raza.value = it },
            label = { Text("Raza") }
        )

        TextField(
            value = edad.value,
            onValueChange = { edad.value = it },
            label = { Text("Edad") }
        )

        TextField(
            value = peso.value,
            onValueChange = { peso.value = it },
            label = { Text("Peso") }
        )

        TextField(
            value = genero.value,
            onValueChange = { genero.value = it },
            label = { Text("Género") }
        )

        Button(
            onClick = {
                // Aquí se llamará a la función para registrar la mascota
                agregarMascotaViewModel.registrarMascota(
                    nombre.value, especie.value, raza.value, edad.value, peso.value, genero.value
                )
            }
        ) {
            Text("Agregar Mascota")
        }
    }
}

@Preview
@Composable
fun AgregarMascotaScreenPreview() {
    AgregarMascotaScreen()
}
