package org.dagger.mvp.ui.login.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.dagger.mvp.network.ApiInterface
import org.dagger.mvp.network.request.LoginRequest
import org.dagger.mvp.ui.login.view.LoginView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginPresenter @Inject constructor(private var apiInterface: ApiInterface) {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var view: LoginView

    fun attachView(view: LoginView) {
        this.view = view
    }

    fun authUser(username: String?, password: String?) {
        view.showProgress()
        if (username.isNullOrBlank()) {
            view.showError("Username is empty")
            view.hideProgress()
            return
        }
        if (password.isNullOrBlank()) {
            view.showError("Password is empty")
            view.hideProgress()
            return
        }
        compositeDisposable.add(
            apiInterface.auth(LoginRequest(username, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.fastHideProgress()
                    view.openRegionFragment()
                }, {
                    view.fastHideProgress()
                    view.openRegionFragment()
                })
        )
    }

    fun unSubscribe() {
        compositeDisposable.dispose()
    }
}