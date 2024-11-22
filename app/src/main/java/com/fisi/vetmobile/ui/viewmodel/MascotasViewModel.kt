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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MascotasViewModel(private val mascotasRepository: MascotasRepository) : ViewModel() {

    private val _mascotas = MutableStateFlow<List<Mascotas>>(emptyList())
    val mascotas: StateFlow<List<Mascotas>> get() = _mascotas


    fun loadMascotas(idusuario: String) {
        if (_mascotas.value.isNotEmpty()) return

        viewModelScope.launch {
            try {
                val result = mascotasRepository.obtenerMascotas(idusuario)
                if (result.isSuccessful) {
                    _mascotas.value = result.body()!!
                } else {
                    // Handle API error
                }
            } catch (e: HttpException) {
                // Handle exception
            }
        }
    }

    fun registrarMascota(mascota: Mascotas) {
        viewModelScope.launch {
            try{
                val result = mascotasRepository.registrarMascota(mascota)
                if(result.code() == 201){
                    _mascotas.value += mascota
                }
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