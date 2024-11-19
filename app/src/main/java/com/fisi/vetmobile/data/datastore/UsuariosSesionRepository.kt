package com.fisi.vetmobile.data.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UsuariosSesionRepository(
    private val dataStore: DataStore<Preferences>
){
    private companion object {
        val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
        val ID_USUARIO = stringPreferencesKey("id_usuario")
        const val TAG = "UsuariosSesionRepository"
    }

    suspend fun saveIsUserLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_USER_LOGGED_IN] = isLoggedIn
        }
    }

    val isUserLoggedIn: Flow<Boolean> = dataStore.data.catch {
        if(it is IOException) {
            Log.e(TAG, "Error leyendo Sesion de Usuario.", it)
            emit(emptyPreferences())
        }else{
            throw it
        }
    }.map { preferences ->
        preferences[IS_USER_LOGGED_IN] ?: false
    }

    suspend fun saveIdUsuario(idUsuario: String) {
        dataStore.edit { preferences ->
            preferences[ID_USUARIO] = idUsuario
        }
    }

    val idusuario_: Flow<String> = dataStore.data.catch {
        if(it is IOException) {
            Log.e(TAG, "Error leyendo Sesion de Usuario.", it)
            emit(emptyPreferences())
        }else{
            throw it
        }
    }.map { preferences ->
        preferences[ID_USUARIO] ?: ""
    }



}
