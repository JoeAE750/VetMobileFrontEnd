package com.fisi.vetmobile.network

import com.fisi.vetmobile.data.model.Usuarios
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

    @POST("usuarios")
    suspend fun registrarUsuario(@Body usuarios: Usuarios): Usuarios

    @PUT("usuarios/{username}")
    suspend fun actualizarUsuario(@Path("username") username: String, @Body usuarios: Usuarios): UsuarioResponse

    @DELETE("usuarios/{username}")
    suspend fun eliminarUsuario(@Path("username") username: String): Response<Unit>

    @POST("mascotas")
    suspend fun createMascota(@Body mascotaRequest: MascotasRequest): MascotasResponse

    @GET("mascotas/usuario")
    suspend fun getMascotasByUser(@Query("id_usuario") idUsuario: String): List<MascotasResponse>

    @GET("mascota")
    suspend fun getMascotaByUserAndName(@Query("id_usuario") idUsuario: String, @Query("nombre") nombre: String): MascotasResponse

    @PUT("mascotas/{id_mascota}")
    suspend fun updateMascota(
        @Path("id_mascota") idMascota: Int,
        @Body mascotaRequest: MascotasRequest
    ): MascotasResponse

    @DELETE("mascotas/{id_mascota}")
    suspend fun deleteMascota(@Path("id_mascota") idMascota: Int)

}