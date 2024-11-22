package com.fisi.vetmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fisi.vetmobile.data.repository.MascotasRegistroRepository
import com.fisi.vetmobile.network.MascotasRequest
import kotlinx.coroutines.launch

class AgregarMascotaViewModel(private val mascotasRegistroRepository: MascotasRegistroRepository) : ViewModel() {

    // Función para registrar una nueva mascota
    fun registrarMascota(
        nombre: String,
        especie: String,
        raza: String,
        edad: String,
        peso: String,
        genero: String
    ) {
        val mascotaRequest = MascotasRequest(
            id_usuario = "123", // Aquí deberías obtener el ID de usuario de sesión actual
            nombre = nombre,
            especie = especie,
            raza = raza,
            edad = edad,
            peso = peso,
            genero = genero
        )

        viewModelScope.launch {
            try {
                // Hacemos la llamada al repositorio para registrar la mascota
                val response = mascotasRegistroRepository.registrarMascota(mascotaRequest)

                if (response.isSuccessful) {
                    // Maneja la respuesta exitosa (por ejemplo, muestra un mensaje de éxito)
                } else {
                    // Maneja el error (por ejemplo, muestra un mensaje de error)
                }
            } catch (e: Exception) {
                // Manejo de excepciones
            }
        }
    }
}
