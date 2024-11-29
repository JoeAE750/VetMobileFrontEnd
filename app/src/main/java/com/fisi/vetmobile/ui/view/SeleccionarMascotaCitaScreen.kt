package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun SeleccionarMascotaCitaScreen() {
    var selectedVeterinary by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Mascotas")
            OutlinedTextField(
                value = selectedVeterinary,
                onValueChange = {},
                readOnly = true,
                interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                showMenu = true
                            }
                        }
                    }
                }
            )
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                offset = DpOffset(x = 0.dp, y = (-1000).dp)
            ) {
                DropdownMenuItem(onClick = {
                    showMenu = false
                    selectedVeterinary = "Cleo"
                }, text = {
                    Text("Cleo")
                })
                DropdownMenuItem(onClick = {
                    showMenu = false
                    selectedVeterinary = "Mai"
                }, text = {
                    Text("Mai")
                })
            }

            Text(text = "Descripcion")
            OutlinedTextField(
                value = text,
                onValueChange = {text = it},
                label = { Text("Da detalles acerca del servicio.") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp)
                    .padding(top = 8.dp),
                singleLine = false,
                maxLines = 5,
            )

            Row {
                Button(
                    onClick = {},
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF98FF98),
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Finalizar Cita")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {},
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF08080),
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Cancelar")
                }
            }


        }
    }
}


@Preview(apiLevel = 34, showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or android.content.res.Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SeleccionarMascotasCitaScreenPreview() {
    SeleccionarMascotaCitaScreen()
}