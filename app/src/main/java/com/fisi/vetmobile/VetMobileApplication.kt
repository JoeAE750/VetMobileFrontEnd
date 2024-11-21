package com.fisi.vetmobile

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.fisi.vetmobile.data.AppContainer
import com.fisi.vetmobile.data.DefaultAppContainer
import com.fisi.vetmobile.data.datastore.UsuariosSesionRepository

private const val  USUARIO_SESION_NAME = "usuario_sesion"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = USUARIO_SESION_NAME
)

class VetMobileApplication : Application() {
    lateinit var container: AppContainer
    lateinit var usuarioSesionRepository: UsuariosSesionRepository
    override fun onCreate() {
        super.onCreate()
        usuarioSesionRepository = UsuariosSesionRepository(dataStore)
        container = DefaultAppContainer()
    }
}