package com.fisi.vetmobile.data.repository

import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.network.VetMobileApiService
import retrofit2.Response

interface MascotasRepository {
    suspend fun registrarMascota(mascota: Mascotas): Response<Mascotas>

    suspend fun obtenerMascotas(idusuario: String): Response<List<Mascotas>>
}

class NetworkMascotasRepository (
    private val vetMobileApiService: VetMobileApiService
): MascotasRepository{

    override suspend fun registrarMascota(mascota: Mascotas):Response<Mascotas> {
        return vetMobileApiService.registrarMascota(mascota)
    }

    override suspend fun obtenerMascotas(idusuario: String): Response<List<Mascotas>> {
        return vetMobileApiService.obtenerMascotas(idusuario)
    }
}

