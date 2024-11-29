package com.fisi.vetmobile.data

import com.fisi.vetmobile.data.repository.CitasRepository
import com.fisi.vetmobile.data.repository.MascotasRepository
import com.fisi.vetmobile.data.repository.NetworkCitasRepository
import com.fisi.vetmobile.data.repository.NetworkMascotasRepository
import com.fisi.vetmobile.data.repository.NetworkUsuariosRepository
import com.fisi.vetmobile.data.repository.UsuariosRepository
import com.fisi.vetmobile.network.VetMobileApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val usuariosRepository: UsuariosRepository
    val mascotasRepository: MascotasRepository
    val citasRepository: CitasRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://vetmobilebackend.onrender.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: VetMobileApiService by lazy {
        retrofit.create(VetMobileApiService::class.java)
    }

    override val usuariosRepository: UsuariosRepository by lazy {
        NetworkUsuariosRepository(retrofitService)
    }

    override val mascotasRepository: MascotasRepository by lazy {
        NetworkMascotasRepository(retrofitService)
    }

    override val citasRepository: CitasRepository by lazy {
        NetworkCitasRepository(retrofitService)
    }

}
