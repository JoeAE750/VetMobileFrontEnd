package com.fisi.vetmobile.data.repository

import com.fisi.vetmobile.data.model.Usuarios
import com.fisi.vetmobile.network.LoginRequest
import com.fisi.vetmobile.network.LoginResponse
import com.fisi.vetmobile.network.VetMobileApiService
import retrofit2.Response

interface UsuariosRepository{
    suspend fun verificarLogin(loginRequest: LoginRequest): LoginResponse

    suspend fun registrarUsuario(usuarios: Usuarios): Response<Usuarios>
}

class NetworkUsuariosRepository(
    private val vetMobileApiService: VetMobileApiService
): UsuariosRepository {
    override suspend fun verificarLogin(loginRequest: LoginRequest): LoginResponse {
        return vetMobileApiService.verificarLogin(loginRequest)
    }

    override suspend fun registrarUsuario(usuarios: Usuarios):Response<Usuarios> {
        return vetMobileApiService.registrarUsuario(usuarios)
    }
}