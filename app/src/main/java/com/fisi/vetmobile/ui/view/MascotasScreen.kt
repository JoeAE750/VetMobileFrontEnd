package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.ui.viewmodel.MascotasViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Cat
import compose.icons.fontawesomeicons.solid.Dog

@Composable
fun MascotasScreen(
    mascotaViewModel: MascotasViewModel = viewModel(factory = MascotasViewModel.Factory),
    idusuario: String,
    onAddMascotaClick: () -> Unit
) {

    val mascotas by mascotaViewModel.mascotas.collectAsState()

    LaunchedEffect(idusuario) {
        mascotaViewModel.loadMascotas(idusuario)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column {
            MascotasList(Modifier.padding(), mascotas)
        }

        FloatingActionButton(
            onClick = onAddMascotaClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Agregar Mascotas")
        }
    }
}

@Composable
fun MascotasList(modifier: Modifier = Modifier, pets: List<Mascotas>) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(pets) { pet ->
            MascotaCard(petName = pet.nombre, petType = pet.especie, petRaza = pet.raza)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MascotaCard(petName: String, petType: String, petRaza: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF263238)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (petType == "1") {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Dog,
                    contentDescription = "Icono Perro",
                    modifier = Modifier.size(48.dp),
                    tint = Color(0xFF4CAF50)
                )
            } else {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Cat,
                    contentDescription = "Icono Gato",
                    modifier = Modifier.size(48.dp),
                    tint = Color(0xFF4CAF50)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = petName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(text = petRaza, fontSize = 14.sp, color = Color.LightGray)
            }
        }
    }
}

@Preview
@Composable
fun PreviewPetApp() {
    //MascotasScreen()
}
