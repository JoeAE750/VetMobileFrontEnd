package com.fisi.vetmobile.data.repository

import com.fisi.vetmobile.data.model.Usuarios
import com.fisi.vetmobile.network.LoginRequest
import com.fisi.vetmobile.network.LoginResponse
import com.fisi.vetmobile.network.VetMobileApiService

interface UsuariosRepository{
    suspend fun verificarLogin(loginRequest: LoginRequest): LoginResponse

    suspend fun registrarUsuario(usuarios: Usuarios): Usuarios
}

class NetworkUsuariosRepository(
    private val VetMobileApiService: VetMobileApiService
): UsuariosRepository {
    override suspend fun verificarLogin(loginRequest: LoginRequest): LoginResponse {
        return VetMobileApiService.verificarLogin(loginRequest)
    }

    override suspend fun registrarUsuario(usuarios: Usuarios): Usuarios {
        return VetMobileApiService.registrarUsuario(usuarios)
    }
}