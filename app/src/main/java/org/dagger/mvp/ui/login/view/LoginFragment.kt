package org.dagger.mvp.ui.login.view

import android.os.Handler
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_login.*
import org.dagger.mvp.R
import org.dagger.mvp.di.components.DaggerFragmentComponent
import org.dagger.mvp.di.modules.FragmentModule
import org.dagger.mvp.ui.base.BaseFragment
import org.dagger.mvp.ui.login.presenter.LoginPresenter
import org.dagger.mvp.ui.region.view.RegionFragment
import org.dagger.mvp.util.setInvisible
import org.dagger.mvp.util.setVisible
import javax.inject.Inject

class LoginFragment : BaseFragment(R.layout.fragment_login, false), LoginView {
    @Inject
    lateinit var loginPresenter: LoginPresenter

    override fun injectDependency() {
        DaggerFragmentComponent
            .builder()
            .fragmentModule(FragmentModule())
            .build()
            .inject(this)
    }

    override fun onViewCreate() {
        loginPresenter.attachView(this)
        cvSign.setOnClickListener {
            loginPresenter.authUser(etLogin.text.toString(), etParol.text.toString())
        }
    }

    override fun fastHideProgress() {
        pbLogin.setInvisible()
    }

    override fun hideProgress() {
        Handler().postDelayed({
            pbLogin?.setInvisible()
        }, 1000)
    }

    override fun showProgress() {
        pbLogin.setVisible()
    }

    override fun openRegionFragment() {
        openFragment(RegionFragment())
    }

    override fun showError(s: String) {
        context?.let { Toast.makeText(it, s, Toast.LENGTH_LONG).show() }
    }

    override fun unSubscribePresenter() {
        loginPresenter.unSubscribe()
    }
}