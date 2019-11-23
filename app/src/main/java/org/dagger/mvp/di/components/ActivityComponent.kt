package org.dagger.mvp.di.components

import dagger.Component
import org.dagger.mvp.di.modules.ActivityModule
import org.dagger.mvp.ui.MainActivity

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}