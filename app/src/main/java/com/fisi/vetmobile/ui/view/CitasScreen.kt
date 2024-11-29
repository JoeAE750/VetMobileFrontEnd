package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fisi.vetmobile.R
import com.fisi.vetmobile.data.model.Citas
import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.data.model.Tipo_Servicios
import com.fisi.vetmobile.data.model.Veterinarios
import com.fisi.vetmobile.ui.viewmodel.CitasViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CitasScreen(
    citaViewModel: CitasViewModel, idusuario: Int, onServicioClick: () -> Unit
) {
    val citas by citaViewModel.citas.collectAsState()
    val servicios by citaViewModel.servicios.collectAsState()
    val veterinarios by citaViewModel.veterinarios.collectAsState()
    val mascotas by citaViewModel.mascotas.collectAsState()

    citaViewModel.updateIdUsuario(idusuario)

    LaunchedEffect(Unit) {
        citaViewModel.loadServicios()
        citaViewModel.loadVeterinarios()
        citaViewModel.loadCitas(idusuario)
        citaViewModel.loadMascotas(idusuario.toString())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        Text(
            text = "Mis Citas",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        ListaCitas(citas = citas, citaViewModel, idusuario, veterinarios,mascotas)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Servicios Disponibles",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CatalogoServicios(
            servicios = servicios, onServicioClick = onServicioClick, citaViewModel = citaViewModel
        )
    }
}


@Composable
fun ListaCitas(
    citas: List<Citas>,
    citaViewModel: CitasViewModel,
    idusuario: Int,
    veterinarios: List<Veterinarios>,
    mascotas: List<Mascotas>
) {
    val maxHeight = LocalConfiguration.current.screenHeightDp.dp * 0.45f
    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .heightIn(max = maxHeight),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(citas) { cita ->
            ItemCita(
                cita = cita,
                onCancelClick = {
                    citaViewModel.eliminarCita(cita.id_cita?.toInt() ?: 0)
                    citaViewModel.loadCitas(idusuario)
                },
                veterinario_nombre = veterinarios.find { it.id_veterinario == cita.id_veterinario }?.nombre
                    ?: "",
                mascota_nombre = mascotas.find { it.id_mascota == cita.id_mascota.toInt() }?.nombre ?: ""
            )
        }
    }
}

@Composable
fun ItemCita(cita: Citas, onCancelClick: () -> Unit, veterinario_nombre: String, mascota_nombre: String) {
    var showMenu by remember { mutableStateOf(false) }
    val parsedDateTime = reverseParseDateTime(cita.fecha_cita.toString())
    val dayOfMonth = parsedDateTime["Day"] ?: ""
    val month = parsedDateTime["Month"] ?: ""
    val time = parsedDateTime["Time"] ?: ""

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, shape = RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
                .clip(MaterialTheme.shapes.medium), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dayOfMonth,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = month,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(10.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
                .width(160.dp), verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = veterinario_nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 1
            )


            Spacer(modifier = Modifier)

            Text(
                text = time, fontWeight = FontWeight.SemiBold, fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier)

        Text(text = mascota_nombre)

        Box(
            modifier = Modifier
                .size(40.dp)
                .padding(start = 0.dp, top = 10.dp, end = 10.dp, bottom = 0.dp),
        ) {
            IconButton(onClick = { showMenu = true }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert, contentDescription = null
                )
            }

            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = {
                    showMenu = false
                    onCancelClick()
                }, text = {
                    Text("Cancelar Cita")
                })
            }
        }
    }
}


fun reverseParseDateTime(dateTime: String): Map<String, String> {
    return try {
        // Update input format for ISO 8601
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(dateTime)

        // Output formats for Day, Month, and Time
        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())

        // Format the time in 12-hour format with AM/PM
        val timeFormat =
            SimpleDateFormat("h:mm a", Locale.getDefault()) // 12-hour format with AM/PM

        mapOf(
            "Day" to dayFormat.format(date),
            "Month" to monthFormat.format(date).uppercase(),
            "Time" to timeFormat.format(date)
        )
    } catch (e: Exception) {
        e.printStackTrace()
        mapOf("Day" to "Error", "Month" to "Error", "Time" to "Error")
    }
}

@Composable
fun CatalogoServicios(
    servicios: List<Tipo_Servicios>, onServicioClick: () -> Unit, citaViewModel: CitasViewModel
) {
    LazyRow {
        items(servicios) { servicio ->
            ItemServicio(
                id = R.drawable.vet_launcher_foreground,
                servicio = servicio,
                onServicioClick = onServicioClick,
                citaViewModel = citaViewModel
            )
        }
    }
}

@Composable
fun ItemServicio(
    id: Int, servicio: Tipo_Servicios, onServicioClick: () -> Unit, citaViewModel: CitasViewModel
) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .size(120.dp, 140.dp)
            .shadow(10.dp, shape = RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .clickable {
                onServicioClick().also {
                    citaViewModel.updateServicio(idservicio = servicio.id_servicio)
                }
            }, horizontalAlignment = Alignment.CenterHorizontally
        //verticalArrangement = Arrangement.Center
    ) {
        val painter = painterResource(id = id)
        Image(
            painter = painter,
            contentDescription = "Foto de Servicio",
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White),
            contentScale = ContentScale.Fit,
            alignment = Alignment.TopCenter
        )
        Text(
            text = servicio.nombre, style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            ), modifier = Modifier.padding(top = 8.dp)
        )
    }
}

/*
@Preview(
    showBackground = true,
    apiLevel = 34,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or android.content.res.Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewCitasScreen() {
    val mockCita = Citas(
        id_cita = "1",
        id_veterinario = "1",
        fecha_cita = "2024-12-04 19:00:00",
        id_mascota = "1",
        razon = "Consulta",
        id_estado = "1",
        id_servicio = "1",
        id_usuario = "1"
    )
    ItemCita(cita = mockCita, onCancelClick = {}, veterinario_nombre = "Dr. John Doe")
}
*/