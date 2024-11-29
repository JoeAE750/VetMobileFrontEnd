package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.fisi.vetmobile.data.model.Citas
import com.fisi.vetmobile.ui.viewmodel.CitasViewModel

@Composable
fun SeleccionarMascotaCitaScreen(
    citaViewModel: CitasViewModel,
    onFinalizarClick: () -> Unit,
    onCancelClick: () -> Unit
) {

    val uiState by citaViewModel.uiState.collectAsState()
    val mascotas by citaViewModel.mascotas.collectAsState()
    var selectedMascota by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Scaffold { paddingValues ->
        // Use a Box to center everything vertically and horizontally
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                // Title for the screen
                Text(
                    text = "Mascota",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Dropdown for selecting pet
                OutlinedTextField(
                    value = selectedMascota,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Seleccionar Mascota") },
                    interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    showMenu = true
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .clip(RoundedCornerShape(8.dp))
                )

                // Dropdown menu for selecting a pet
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                    offset = DpOffset(x = 0.dp, y = (-1000).dp)
                ) {
                    mascotas.forEach { mascota ->
                        DropdownMenuItem(onClick = {
                            selectedMascota = mascota.nombre
                            citaViewModel.updateMascota(mascota.id_mascota.toString())
                            showMenu = false
                        }, text = {
                            Text(mascota.nombre)
                        })
                    }
                }

                // Description section
                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        citaViewModel.updateRazon(text)
                    },
                    label = { Text("Detalles acerca del servicio") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 150.dp)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    singleLine = false,
                    maxLines = 5,
                )

                // Create new Cita
                val newCita = Citas(
                    id_mascota = uiState.id_mascota,
                    id_veterinario = uiState.id_veterinario.toString(),
                    fecha_cita = uiState.timestamp,
                    razon = uiState.razon,
                    id_estado = "1",
                    id_servicio = uiState.id_servicio,
                    id_usuario = uiState.id_usuario
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Buttons for finalizar and cancelar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 35.dp),
                ) {
                    Button(
                        onClick = {
                            onFinalizarClick().also {
                                citaViewModel.crearCita(newCita)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.45f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF98FF98),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Finalizar Cita",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            onCancelClick().also {
                                citaViewModel.limpiarUiState()
                            }
                        },
                        modifier = Modifier
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF08080),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Cancelar",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}


/*
@Preview(apiLevel = 34, showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or android.content.res.Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SeleccionarMascotasCitaScreenPreview() {
    SeleccionarMascotaCitaScreen()
}
*/