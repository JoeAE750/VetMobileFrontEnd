package com.fisi.vetmobile.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fisi.vetmobile.VetMobileApplication
import com.fisi.vetmobile.data.datastore.UsuariosSesionRepository
import com.fisi.vetmobile.data.model.Usuarios
import com.fisi.vetmobile.data.repository.UsuariosRepository
import com.fisi.vetmobile.network.LoginRequest
import com.fisi.vetmobile.ui.uistate.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException

class LoginViewModel(
    private val usuariosRepository: UsuariosRepository,
    private val usuariosSesionRepository: UsuariosSesionRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> =
        combine(
            usuariosSesionRepository.isUserLoggedIn,
            usuariosSesionRepository.idusuario_
        ) { isUserLoggedIn, idUsuario_ ->
            LoginUiState(
                isLoginSuccesfull = isUserLoggedIn,
                idusuario = idUsuario_
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LoginUiState()
        )




    private val _username = MutableLiveData("")
    var username: LiveData<String> = _username

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    private val _contrasena = MutableLiveData("")
    var contrasena: LiveData<String> = _contrasena

    fun updateContrasena(newContrasena: String) {
        _contrasena.value = newContrasena
    }


    fun validarLogin(username: String, contrasena: String) {
        val loginRequest = LoginRequest(username, contrasena)
        viewModelScope.launch {
            try {
                val result = usuariosRepository.verificarLogin(loginRequest)
                if (result.status == 1) {
                    usuariosSesionRepository.saveIsUserLoggedIn(true)
                    usuariosSesionRepository.saveIdUsuario(result.id_usuario)
                } else {
                    usuariosSesionRepository.saveIsUserLoggedIn(false)
                }
            } catch (e: HttpException) {
                usuariosSesionRepository.saveIsUserLoggedIn(false)
            } catch (e: SocketTimeoutException) {
                usuariosSesionRepository.saveIsUserLoggedIn(false)
            }
        }
    }

    fun registrarUsuario(usuario: Usuarios) {
        viewModelScope.launch {
            try {
                val result = usuariosRepository.registrarUsuario(usuario)
                if (result.code() == 201) {
                    usuariosSesionRepository.saveIsUserLoggedIn(true)
                    usuariosSesionRepository.saveIdUsuario(result.body()?.id.toString())
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(isRegisterSuccesfull = false)
                    }
                }
            } catch (e: HttpException) {
                _uiState.update { currentState ->
                    currentState.copy(isRegisterSuccesfull = false)
                }
            } catch (e: SocketTimeoutException) {
                _uiState.update { currentState ->
                    currentState.copy(isRegisterSuccesfull = false)
                }
            }
        }

    }

    fun cerrarSesion() {
        viewModelScope.launch {
            usuariosSesionRepository.saveIsUserLoggedIn(false)
            usuariosSesionRepository.saveIdUsuario("")
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as VetMobileApplication)
                val usuariosRepository = application.container.usuariosRepository
                LoginViewModel(
                    usuariosRepository = usuariosRepository,
                    usuariosSesionRepository = application.usuarioSesionRepository
                )
            }
        }
    }
}

