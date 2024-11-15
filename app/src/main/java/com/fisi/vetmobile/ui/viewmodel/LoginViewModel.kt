
package com.fisi.vetmobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fisi.vetmobile.VetMobileApplication
import com.fisi.vetmobile.data.repository.UsuariosRepository
import com.fisi.vetmobile.network.LoginRequest
import com.fisi.vetmobile.ui.components.ConexionUIState
import com.fisi.vetmobile.ui.uistate.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val usuariosRepository: UsuariosRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    var username: String by mutableStateOf("")
    var contrasena: String by mutableStateOf("")

    var loginUIConexion: ConexionUIState by mutableStateOf(ConexionUIState.Success) // Cambiar a Success por defecto
        private set

    fun validarLogin(username: String, contrasena: String) {
        val loginRequest = LoginRequest(username, contrasena)
        loginUIConexion = ConexionUIState.Loading // Cambia a Loading cuando comienza la validación
        viewModelScope.launch {
            try {
                val result = usuariosRepository.verificarLogin(loginRequest)
                if (result.status == 1) {
                    _uiState.update { currentState ->
                        currentState.copy(isLoginSuccesfull = true)
                    }
                    loginUIConexion = ConexionUIState.Success // Cambia a Success después de un login exitoso
                } else {
                    loginUIConexion = ConexionUIState.Error // Cambia a Error si falla
                }
            } catch (e: HttpException) {
                loginUIConexion = ConexionUIState.Error // Cambia a Error en caso de excepción
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as VetMobileApplication)
                val usuariosRepository = application.container.usuariosRepository
                LoginViewModel(usuariosRepository = usuariosRepository)
            }
        }
    }
}

