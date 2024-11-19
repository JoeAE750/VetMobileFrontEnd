package com.fisi.vetmobile.navigation

import androidx.annotation.StringRes
import com.fisi.vetmobile.R

enum class VetMobileScreen(@StringRes val vista: Int){
        Welcome(vista = R.string.app_name),
        Login(vista = R.string.login),
        RegistroUsuario(vista = R.string.registrousuario),
        Mascotas(vista = R.string.mascotas),
        RegistroMascota(vista = R.string.registromascota),
}