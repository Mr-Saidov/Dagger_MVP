package org.dagger.mvp.di.components

import dagger.Component
import org.dagger.mvp.di.modules.FragmentModule
import org.dagger.mvp.ui.branch.view.BranchFragment
import org.dagger.mvp.ui.login.view.LoginFragment
import org.dagger.mvp.ui.region.view.RegionFragment

@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(loginFragment: LoginFragment)

    fun inject(regionFragment: RegionFragment)

    fun inject(branchFragment: BranchFragment)
}