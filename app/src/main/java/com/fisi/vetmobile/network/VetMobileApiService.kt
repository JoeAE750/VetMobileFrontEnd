package com.fisi.vetmobile.network

import com.fisi.vetmobile.data.model.Citas
import com.fisi.vetmobile.data.model.Mascotas
import com.fisi.vetmobile.data.model.Tipo_Servicios
import com.fisi.vetmobile.data.model.Usuarios
import com.fisi.vetmobile.data.model.Veterinarios
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

    @GET("tipo_servicio")
    suspend fun obtenerServicios(): Response<List<Tipo_Servicios>>

    @GET("citas/usuario/{id_usuario}")
    suspend fun getCitas(@Path("id_usuario") idusuario: Int): Response<List<Citas>>

    @DELETE("citas/{id_cita}")
    suspend fun eliminarCita(@Path("id_cita") idcita: Int)

    @GET("veterinarios")
    suspend fun getVeterinarios(): Response<List<Veterinarios>>

    @POST("citas")
    suspend fun crearCita(@Body cita: Citas): Response<Citas>
}