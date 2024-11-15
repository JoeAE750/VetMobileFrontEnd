
package com.fisi.vetmobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fisi.vetmobile.data.model.Usuarios
import com.fisi.vetmobile.network.LoginRequest
import com.fisi.vetmobile.network.VetMobileApi
import com.fisi.vetmobile.ui.components.ConexionUIState
import com.fisi.vetmobile.ui.uistate.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
class LoginViewModel : ViewModel() {
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
                val listResult = VetMobileApi.retrofitService.verificarLogin(loginRequest)
                if (listResult.status == 1) {
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
}

