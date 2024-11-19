
package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fisi.vetmobile.data.model.Mascotas // Importa la clase Mascotas
import com.fisi.vetmobile.ui.viewmodel.LoginViewModel
import com.fisi.vetmobile.ui.viewmodel.MascotasViewModel

@Composable
fun RegistrarMascotaScreen(
    mascotasViewModel: MascotasViewModel = viewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = especie,
            onValueChange = { especie = it },
            label = { Text("Especie") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = raza,
            onValueChange = { raza = it },
            label = { Text("Raza") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = genero,
            onValueChange = { genero = it },
            label = { Text("Género") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val nuevaMascota = Mascotas(
                idmascota = "",  // Este campo podría generarse automáticamente o recibir un valor único desde el backend
                idusuario = "",  // Este campo también debería obtenerse del usuario logueado
                nombre = nombre,
                especie = especie,
                raza = raza,
                edad = edad,
                peso = peso,
                genero = genero
            )
            mascotasViewModel.registrarMascota(nuevaMascota)
        }) {
            Text("Registrar Mascota")
        }
    }
}

@Preview
@Composable
fun RegistrarMascotaScreenPreview() {
    RegistrarMascotaScreen()
}