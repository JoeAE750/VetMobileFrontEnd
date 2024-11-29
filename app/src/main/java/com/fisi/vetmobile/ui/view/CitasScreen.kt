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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fisi.vetmobile.R
import com.fisi.vetmobile.data.model.Citas
import com.fisi.vetmobile.data.model.Tipo_Servicios
import com.fisi.vetmobile.data.model.Veterinarios
import com.fisi.vetmobile.ui.viewmodel.CitasViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.text.split

@Composable
fun CitasScreen(
    citaViewModel: CitasViewModel = viewModel(factory = CitasViewModel.Factory, key = "citasViewModel"),
    idusuario: Int,
    onServicioClick: () -> Unit
) {
    val citas by citaViewModel.citas.collectAsState()
    val servicios by citaViewModel.servicios.collectAsState()
    val veterinarios by citaViewModel.veterinarios.collectAsState()

    LaunchedEffect(Unit) {
        citaViewModel.loadServicios()
        citaViewModel.loadVeterinarios()
        citaViewModel.loadCitas(idusuario)
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


        ListaCitas(citas = citas, citaViewModel, idusuario, veterinarios)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Servicios Disponibles",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CatalogoServicios(servicios = servicios, onServicioClick, citaViewModel = citaViewModel)
    }
}


@Composable
fun ListaCitas(citas: List<Citas>, citaViewModel: CitasViewModel, idusuario: Int, veterinarios: List<Veterinarios>) {
    val maxHeight = LocalConfiguration.current.screenHeightDp.dp * 0.45f
    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .heightIn(max = maxHeight),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(citas) { cita ->
            ItemCita(cita = cita, onCancelClick = {
                citaViewModel.eliminarCita(cita.id_cita.toInt())
                citaViewModel.loadCitas(idusuario)
            },veterinarios)
        }
    }
}

@Composable
fun ItemCita(cita: Citas, onCancelClick: () -> Unit, veterinarios: List<Veterinarios>) {
    var showMenu by remember { mutableStateOf(false) }
    val parsedDateTime = reverseParseDateTime(
        cita.fecha_cita
    )
    val dateParts = parsedDateTime["Date"]?.split(", ")
    val dayOfMonth = dateParts?.get(1)?.split(" ")?.get(0)
    val month = dateParts?.get(1)?.split(" ")?.get(1)
    val time = parsedDateTime["Time"]

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, shape = RoundedCornerShape(12.dp))
            .background(Color.White), horizontalArrangement = Arrangement.SpaceEvenly
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
                    text = dayOfMonth ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = month ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(10.dp, top = 10.dp, bottom = 10.dp, end = 0.dp)
                .width(160.dp), verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = veterinarios.find {  it.id_veterinario == cita.id_veterinario }?.nombre ?: "", fontWeight = FontWeight.Bold, fontSize = 18.sp, maxLines = 1
            )

            Text(
                text = veterinarios.find { it.id_veterinario == cita.id_veterinario }?.especializacion ?:"", fontWeight = FontWeight.Normal, fontSize = 15.sp
            )

            Spacer(modifier = Modifier)

            Text(
                text = time ?: "", fontWeight = FontWeight.SemiBold, fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier)

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
                    onCancelClick
                }, text = {
                    Text("Cancel Appointment")
                })
            }
        }
    }
}


fun reverseParseDateTime(timestamp: String): Map<String, String> {
    // Define the formatter for the complete timestamp pattern (yyyy-MM-dd HH:mm:ss)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

    // Parse the timestamp into a LocalDateTime object
    val parsedDateTime = LocalDateTime.parse(timestamp, formatter)

    // Format the date part into "EEEE, d MMM" format (e.g., "Wednesday, 4 Dec")
    val dateFormatter = DateTimeFormatter.ofPattern("EEEE, d MMM", Locale.ENGLISH)
    val formattedDate = parsedDateTime.format(dateFormatter)

    // Format the time part into "h:mm a" format (e.g., "10:00 AM")
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
    val formattedTime = parsedDateTime.format(timeFormatter)

    // Return the result as a map
    return mapOf(
        "Date" to formattedDate,
        "Time" to formattedTime
    )
}


@Composable
fun CatalogoServicios(
    servicios: List<Tipo_Servicios>,
    onServicioClick: () -> Unit,
    citaViewModel: CitasViewModel
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
    id: Int,
    servicio: Tipo_Servicios,
    onServicioClick: () -> Unit,
    citaViewModel: CitasViewModel
) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .size(120.dp, 140.dp)
            .shadow(10.dp, shape = RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.onBackground)
            .clickable {
                onServicioClick.also {
                    citaViewModel.updateServicio(servicio.id_servicio)
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
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
    CitasScreen()
}
*/