package org.dagger.mvp.di.modules

import dagger.Module
import dagger.Provides
import org.dagger.mvp.application.App
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApplication() = app
}