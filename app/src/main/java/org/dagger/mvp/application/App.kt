package org.dagger.mvp.application

import android.app.Application
import org.dagger.mvp.di.components.AppComponent

class App : Application() {
    private lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getApplicationComponent() = component

    companion object {
        lateinit var instance: App private set
    }
}