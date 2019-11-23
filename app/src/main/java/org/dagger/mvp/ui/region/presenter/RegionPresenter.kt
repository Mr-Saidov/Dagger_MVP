package org.dagger.mvp.ui.region.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.dagger.mvp.network.ApiInterface
import org.dagger.mvp.ui.region.view.RegionView
import javax.inject.Inject

class RegionPresenter @Inject constructor(private var apiInterface: ApiInterface) {
    private lateinit var view: RegionView
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun attachView(view: RegionView) {
        this.view = view
    }

    fun loadRegions() {
        view.showProgress()
        compositeDisposable.add(
            apiInterface.getRegions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showRegions(it.data)
                    view.hideProgress()
                }, {
                    view.hideProgress()
                    it.message?.let { view.showError(it) }
                })
        )
    }

    fun unSubscribe() {
        compositeDisposable.dispose()
    }
}