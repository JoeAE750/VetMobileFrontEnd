package com.fisi.vetmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fisi.vetmobile.VetMobileApplication
import com.fisi.vetmobile.data.model.Citas
import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.data.model.Tipo_Servicios
import com.fisi.vetmobile.data.model.Veterinarios
import com.fisi.vetmobile.data.repository.CitasRepository
import com.fisi.vetmobile.data.repository.MascotasRepository
import com.fisi.vetmobile.ui.uistate.CitasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CitasViewModel(
    private val citasRepository: CitasRepository,
    private val mascotasRepository: MascotasRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CitasUiState())
    val uiState: StateFlow<CitasUiState> = _uiState.asStateFlow()

    private val _servicios = MutableStateFlow<List<Tipo_Servicios>>(emptyList())
    val servicios: StateFlow<List<Tipo_Servicios>> get() = _servicios

    private val _citas = MutableStateFlow<List<Citas>>(emptyList())
    val citas: StateFlow<List<Citas>> get() = _citas

    private val _veterinarios = MutableStateFlow<List<Veterinarios>>(emptyList())
    val veterinarios: StateFlow<List<Veterinarios>> get() = _veterinarios

    private val _mascotas = MutableStateFlow<List<Mascotas>>(emptyList())
    val mascotas: StateFlow<List<Mascotas>> get() = _mascotas

    fun limpiarUiState(){
        _uiState.value = CitasUiState()
    }

    fun loadServicios() {
        viewModelScope.launch {
            val result = citasRepository.obtenerServicios()
            if (result.isSuccessful) {
                _servicios.value = result.body()!!
            }
        }
    }

    fun loadCitas(idusuario: Int) {
        viewModelScope.launch {
            val result = citasRepository.getCitas(idusuario)
            if (result.isSuccessful) {
                _citas.value = result.body()!!
            }
        }
    }

    fun loadVeterinarios() {
        viewModelScope.launch {
            val result = citasRepository.getVeterinarios()
            if (result.isSuccessful) {
                _veterinarios.value = result.body()!!
            }
        }
    }

    fun loadMascotas(idusuario: String) {
        viewModelScope.launch {
            val result = mascotasRepository.obtenerMascotas(idusuario)
            if (result.isSuccessful) {
                _mascotas.value = result.body()!!
            }
        }
    }


    fun crearCita(cita: Citas) {
        viewModelScope.launch {
            citasRepository.crearCita(cita)
        }
    }

    fun eliminarCita(id_cita: Int) {
        viewModelScope.launch {
            citasRepository.eliminarCita(id_cita)
        }
    }

    fun updateIdUsuario(newIdUsuario: Int) {
        _uiState.update { currentState ->
            currentState.copy(id_usuario = newIdUsuario.toString())
        }
    }

    fun updateServicio(idservicio: String) {
        _uiState.update { currentState ->
            currentState.copy(id_servicio = idservicio)
        }
    }

    fun updateTime(timestamp: String) {
        _uiState.update { currentState ->
            currentState.copy(timestamp = timestamp)
        }
    }

    fun updateVeterinario(newVeterinario: Int) {
        _uiState.update { currentState ->
            currentState.copy(id_veterinario = newVeterinario)
        }
    }

    fun updateMascota(newMascota: String) {
        _uiState.update { currentState ->
            currentState.copy(id_mascota = newMascota)
        }
    }

    fun updateRazon(newRazon: String) {
        _uiState.update { currentState ->
            currentState.copy(razon = newRazon)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as VetMobileApplication)
                val citasRepository = application.container.citasRepository
                val mascotasRepository = application.container.mascotasRepository
                CitasViewModel(
                    citasRepository = citasRepository,
                    mascotasRepository = mascotasRepository
                )
            }
        }
    }

}

