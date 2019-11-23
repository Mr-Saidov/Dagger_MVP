package org.dagger.mvp.di.components

import dagger.Component
import org.dagger.mvp.application.App
import org.dagger.mvp.di.modules.ApplicationModule

@Component(modules = [ApplicationModule::class])
interface AppComponent {
    fun inject(app: App)
}