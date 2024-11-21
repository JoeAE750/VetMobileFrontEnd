/*package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.ui.theme.VetMobileTheme

@Composable
fun MascotasScreen() {
    val mascotas = listOf(
        Mascotas(
            idmascota = "1",
            idusuario = "123",
            nombre = "Rex",
            especie = "Perro",
            raza = "Labrador",
            edad = "3 años",
            peso = "30 kg",
            genero = "Masculino"
        ),
        Mascotas(
            idmascota = "2",
            idusuario = "456",
            nombre = "Bella",
            especie = "Gato",
            raza = "Siamés",
            edad = "2 años",
            peso = "5 kg",
            genero = "Femenino"
        ),
        Mascotas(
            idmascota = "3",
            idusuario = "789",
            nombre = "Max",
            especie = "Perro",
            raza = "Bulldog",
            edad = "4 años",
            peso = "25 kg",
            genero = "Masculino"
        ),
        Mascotas(
            idmascota = "3",
            idusuario = "789",
            nombre = "Max",
            especie = "Perro",
            raza = "Bulldog",
            edad = "4 años",
            peso = "25 kg",
            genero = "Masculino"
        ),
        Mascotas(
            idmascota = "3",
            idusuario = "789",
            nombre = "Max",
            especie = "Perro",
            raza = "Bulldog",
            edad = "4 años",
            peso = "25 kg",
            genero = "Masculino"
        ),
        Mascotas(
            idmascota = "3",
            idusuario = "789",
            nombre = "Max",
            especie = "Perro",
            raza = "Bulldog",
            edad = "4 años",
            peso = "25 kg",
            genero = "Masculino"
        )
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // List of mascotas
        MascotasList(mascotas)

        // Floating action button on top of the list
        FloatingActionButton(
            onClick = { /* Handle click */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp), // Padding from edges
            elevation = FloatingActionButtonDefaults.elevation(8.dp) // Elevation effect
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
                tint = Color.White
            )
        }
    }
}



@Composable
fun MascotaItem(mascota: Mascotas) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAF5FF)) // Light purple background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // Mascot Name
            Text(
                text = mascota.nombre,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50) // Dark text color
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Mascot Type (team/group)
            Text(
                text = mascota.especie,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF8E44AD) // Purple accent color
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mascot Description
            Text(
                text = mascota.raza,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF7F8C8D),
                    lineHeight = 24.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp) // Adjusts the position within the card
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More options",
                    tint = Color(0xFF2C3E50) // Dark color for the icon
                )
            }
        }
    }
}

@Composable
fun MascotasList(mascotas: List<Mascotas>) {
    LazyColumn {
        items(mascotas) { mascota ->
            MascotaItem(mascota)
        }
    }
}


    @Preview
    @Composable
    fun MascotasScreenPreview() {
        VetMobileTheme(darkTheme = false) {
            MascotasScreen()
        }
    }
*/


package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun MascotasScreen(mascotaViewModel: MascotasViewModel = viewModel(factory = MascotasViewModel.Factory), idusuario:String) {

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
            onClick = {  },
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
            if (petType == "1"){
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Dog,
                    contentDescription = "Icono Perro",
                    modifier = Modifier.size(48.dp),
                    tint = Color(0xFF4CAF50))
            }else{
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Cat,
                    contentDescription = "Icono Gato",
                    modifier = Modifier.size(48.dp),
                    tint = Color(0xFF4CAF50))
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
