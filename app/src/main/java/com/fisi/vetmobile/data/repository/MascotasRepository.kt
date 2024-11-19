package com.fisi.vetmobile.data.repository

import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.network.VetMobileApiService

interface MascotasRepository {
    suspend fun registrarMascota(mascota: Mascotas)
}

class NetworkMascotasRepository (
    private val VetMobileApiService: VetMobileApiService
): MascotasRepository{
    override suspend fun registrarMascota(mascota: Mascotas) {
        return VetMobileApiService.registrarMascota(mascota)
    }
}

