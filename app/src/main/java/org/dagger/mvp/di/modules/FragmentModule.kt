package org.dagger.mvp.di.modules

import dagger.Module
import dagger.Provides
import org.dagger.mvp.network.ApiInterface
import org.dagger.mvp.ui.branch.presenter.BranchPresenter
import org.dagger.mvp.ui.login.presenter.LoginPresenter
import org.dagger.mvp.ui.region.presenter.RegionPresenter


@Module
class FragmentModule {

    @Provides
    fun provideLoginPresenter(apiInterface: ApiInterface): LoginPresenter {
        return LoginPresenter(apiInterface)
    }

    @Provides
    fun provideRegionPresenter(apiInterface: ApiInterface): RegionPresenter {
        return RegionPresenter(apiInterface)
    }

    @Provides
    fun provideBranchPresenter(apiInterface: ApiInterface): BranchPresenter {
        return BranchPresenter(apiInterface)
    }

    @Provides
    fun provideApiService(): ApiInterface {
        return ApiInterface.create()
    }
}