package com.fisi.vetmobile.data.repository

import com.fisi.vetmobile.data.model.Citas
import com.fisi.vetmobile.data.model.Tipo_Servicios
import com.fisi.vetmobile.data.model.Veterinarios
import com.fisi.vetmobile.network.VetMobileApiService
import retrofit2.Response

interface CitasRepository {
    suspend fun obtenerServicios(): Response<List<Tipo_Servicios>>
    suspend fun getCitas(idusuario: Int): Response<List<Citas>>
    suspend fun eliminarCita(idcita: Int)
    suspend fun getVeterinarios(): Response<List<Veterinarios>>
    suspend fun crearCita(cita: Citas): Response<Citas>
}

class NetworkCitasRepository(
    private val vetMobileApiService: VetMobileApiService
): CitasRepository{
    override suspend fun obtenerServicios(): Response<List<Tipo_Servicios>> {
        return vetMobileApiService.obtenerServicios()
    }

    override suspend fun getCitas(idusuario: Int): Response<List<Citas>> {
        return vetMobileApiService.getCitas(idusuario)
    }

    override suspend fun eliminarCita(idcita: Int) {
        return vetMobileApiService.eliminarCita(idcita)
    }
    override suspend fun getVeterinarios(): Response<List<Veterinarios>> {
        return vetMobileApiService.getVeterinarios()
    }

    override suspend fun crearCita(cita: Citas): Response<Citas> {
        return vetMobileApiService.crearCita(cita)
    }

}