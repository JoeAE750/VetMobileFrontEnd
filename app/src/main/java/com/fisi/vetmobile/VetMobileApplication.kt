package com.fisi.vetmobile

import android.app.Application
import com.fisi.vetmobile.data.AppContainer
import com.fisi.vetmobile.data.DefaultAppContainer

class VetMobileApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}