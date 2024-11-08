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
}