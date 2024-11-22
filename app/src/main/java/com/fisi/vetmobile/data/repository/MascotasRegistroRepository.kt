package com.fisi.vetmobile.data.repository

import com.fisi.vetmobile.network.MascotasRequest
import com.fisi.vetmobile.network.VetMobileApiService
import retrofit2.Response

// Interfaz para manejar el registro de mascotas
interface MascotasRegistroRepository {
    suspend fun registrarMascota(mascotaRequest: MascotasRequest): Response<Void>
}


class NetworkMascotasRegistroRepository(private val apiService: VetMobileApiService) : MascotasRegistroRepository {

    // Implementación del método de registrar mascota
    override suspend fun registrarMascota(mascotaRequest: MascotasRequest): Response<Void> {
        return apiService.registrarMascota(mascotaRequest)
    }
}