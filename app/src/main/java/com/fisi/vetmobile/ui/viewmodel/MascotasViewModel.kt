/*
package com.fisi.vetmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.ui.uistate.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MascotasViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        loadMascotas()
    }

    private fun loadMascotas() {
        viewModelScope.launch {
            try{

            }catch (e: HttpException){

            }
        }
    }

    fun addMascota(mascota: Mascota) {
        viewModelScope.launch {
            try{

            }catch (e: HttpException){

            }
        }
    }

    fun updateMascota(mascota: Mascota) {
        viewModelScope.launch {
            try{

            }catch (e: HttpException){

            }
        }
    }

    fun deleteMascota(mascota: Mascota) {
        viewModelScope.launch {
            try{

            }catch (e: HttpException){

            }
        }
    }
}*/
package com.fisi.vetmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.network.VetMobileApi
import com.fisi.vetmobile.network.MascotasRequest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MascotasViewModel : ViewModel() {

    fun registrarMascota(
        idUsuario: String,
        nombre: String,
        especie: String,
        raza: String,
        edad: String,
        peso: String,
        genero: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        // Crea una instancia de MascotasRequest con los parámetros correctos
        val request = MascotasRequest(
            id_usuario = idUsuario,
            nombre = nombre,
            especie = especie,
            raza = raza,
            edad = edad,
            peso = peso,
            genero = genero
        )

        viewModelScope.launch {
            try {
                val response = VetMobileApi.retrofitService.createMascota(request)
                if (response != null) {
                    onSuccess() // Navegar a la pantalla anterior en caso de éxito
                } else {
                    onError("Error en el registro de la mascota")
                }
            } catch (e: IOException) {
                onError("Error de conexión")
            } catch (e: HttpException) {
                onError("Error en la respuesta del servidor")
            }
        }
    }
}

