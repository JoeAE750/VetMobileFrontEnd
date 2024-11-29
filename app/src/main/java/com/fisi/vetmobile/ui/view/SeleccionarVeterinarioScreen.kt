package com.fisi.vetmobile.ui.view

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fisi.vetmobile.ui.viewmodel.CitasViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
fun SeleccionarVeterinarioScreen(
    citaViewModel: CitasViewModel = viewModel(
        factory = CitasViewModel.Factory,
        key = "citasViewModel"
    )
) {
    val veterinarios by citaViewModel.veterinarios.collectAsState()

    var showMenu by remember { mutableStateOf(false) }
    var selectedDate = rememberSaveable { mutableStateOf("") }
    val defaultHour = "1:00 PM"
    var selectedHour by remember { mutableStateOf(defaultHour) }
    var selectedVeterinary by remember { mutableStateOf("") }


    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            val context = LocalContext.current
            Text(text = "Veterinario")
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
                veterinarios.forEach { veterinario ->
                    DropdownMenuItem(onClick = {
                        showMenu = false
                        selectedVeterinary = veterinario.nombre
                    }, text = {
                        Text(veterinario.nombre)
                    })
                }
            }

            Text(text = "Fecha")
            OutlinedTextField(
                value = selectedDate.value,
                interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                showDatePickerDialog(selectedDate, context)
                            }
                        }
                    }
                },
                onValueChange = { },
                label = { Text("Select Date") }
            )
            Text(text = "Hora")


            seleccionarHoraCita(
                startTime = LocalTime.of(17, 0),
                endTime = LocalTime.of(20, 30),
                currentSelectedDate = selectedHour,
                onDateSelected = { selectedHour = it },
                onHourSelected = {
                    selectedHour = it
                })

            val timestamp = combineDateAndHour(selectedDate.value, selectedHour)


            Row {
                Button(
                    onClick = {
                        citaViewModel.updateTime(timestamp)
                        citaViewModel.updateVeterinario(selectedVeterinary)
                    },
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF98FF98),
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Aceptar")
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


fun combineDateAndHour(date: String, hour: String): String {
    try {
        val dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")

        // Parse date and hour
        val localDate = LocalDate.parse(date, dateFormatter)
        val localTime = LocalTime.parse(hour, timeFormatter)

        // Combine into a LocalDateTime
        val dateTime = LocalDateTime.of(localDate, localTime)

        // Format to the database timestamp format
        val dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return dateTime.format(dbFormatter)
    } catch (e: Exception) {
        e.printStackTrace()
        return "" // Return empty if parsing fails
    }
}

fun showDatePickerDialog(selectedDate: MutableState<String>, context: Context) {

    // Get current date details for setting the default date in the picker
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Show DatePickerDialog
    DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            // When a date is selected, update the state
            selectedDate.value = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year, month, day
    ).show()
}


@Composable
fun seleccionarHoraCita(
    startTime: LocalTime,
    endTime: LocalTime,
    currentSelectedDate: String,
    modifier: Modifier = Modifier,
    onDateSelected: (String) -> Unit,
    onHourSelected: (String) -> Unit
) {
    val hoursList = generarListaHoras(startTime, endTime)

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(0.dp)
    ) {
        items(hoursList) { hour ->
            seleccionHora(
                title = hour,
                currentToggleState = currentSelectedDate,
                onClickButton = {
                    onDateSelected(hour)
                    onHourSelected(it)
                }
            )

        }
    }
}


@Composable
fun seleccionHora(
    title: String,
    currentToggleState: String,
    onClickButton: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .size(width = 120.dp, height = 40.dp)
            .shadow(3.dp, shape = RoundedCornerShape(10.dp))
            .clickable { onClickButton(title) }
            .background(
                color = if (title != currentToggleState) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(5.dp)
            )

    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (title != currentToggleState) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

fun generarListaHoras(startTime: LocalTime, endTime: LocalTime): List<String> {
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
    val hoursList = mutableListOf<String>()
    var time = startTime

    while (time.isBefore(endTime) || time == endTime) {
        hoursList.add(time.format(formatter))
        time = time.plusMinutes(30)
    }

    return hoursList
}

@Preview
@Composable
fun SeleccionarVeterinarioScreenPreview() {
    SeleccionarVeterinarioScreen()
}
