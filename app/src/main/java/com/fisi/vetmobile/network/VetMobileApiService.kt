package com.fisi.vetmobile.network

import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.data.model.Usuarios
import retrofit2.Response
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
    suspend fun registrarUsuario(@Body usuarios: Usuarios): Response<Usuarios>

    @POST("mascotas")
    suspend fun registrarMascota(@Body mascotas: Mascotas): Response<Mascotas>

    @GET("mascotas/usuario")
    suspend fun obtenerMascotas(@Query("id_usuario") idusuario: String): Response<List<Mascotas>>

}