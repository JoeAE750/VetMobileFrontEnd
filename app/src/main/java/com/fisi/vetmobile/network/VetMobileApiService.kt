package com.fisi.vetmobile.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface VetMobileApiService {

    @POST("login")
    suspend fun verificarLogin(@Body loginRequest: LoginRequest): LoginResponse

    // Create mascota
    @POST("mascotas")
    suspend fun createMascota(@Body mascotaRequest: MascotasRequest): MascotasResponse

    // Get mascotas
    @GET("mascotas/usuario")
    suspend fun getMascotasByUser(@Query("id_usuario") idUsuario: String): List<MascotasResponse>

    // Get mnascota
    @GET("mascota")
    suspend fun getMascotaByUserAndName(@Query("id_usuario") idUsuario: String, @Query("nombre") nombre: String): MascotasResponse

    // Update an existing mascota by its ID
    @PUT("mascotas/{id_mascota}")
    suspend fun updateMascota(
        @Path("id_mascota") idMascota: Int,
        @Body mascotaRequest: MascotasRequest
    ): MascotasResponse

    // Delete a mascota by its ID
    @DELETE("mascotas/{id_mascota}")
    suspend fun deleteMascota(@Path("id_mascota") idMascota: Int): ResponseMessage

}