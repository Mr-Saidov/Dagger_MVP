package org.dagger.mvp.ui.branch.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.dagger.mvp.network.ApiInterface
import org.dagger.mvp.network.request.BranchRequest
import org.dagger.mvp.ui.branch.view.BranchView
import javax.inject.Inject

class BranchPresenter @Inject constructor(private val apiInterface: ApiInterface) {
    private lateinit var view: BranchView
    private var compositeDisposable = CompositeDisposable()

    fun attachView(view: BranchView) {
        this.view = view
    }

    fun loadBranchs(id: Int) {
        view.showProgress()
        compositeDisposable.add(
            apiInterface.getBranchs(BranchRequest(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showBranchs(it.data)
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