package com.fisi.vetmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fisi.vetmobile.VetMobileApplication
import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.data.repository.MascotasRepository
import com.fisi.vetmobile.ui.uistate.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MascotasViewModel(private val mascotasRepository: MascotasRepository) : ViewModel() {

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

    fun registrarMascota(mascota: Mascotas) {
        viewModelScope.launch {
            try{

            }catch (e: HttpException){

            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as VetMobileApplication)
                val mascotasRepository = application.container.mascotasRepository
                MascotasViewModel(mascotasRepository = mascotasRepository)
            }
        }
    }
}