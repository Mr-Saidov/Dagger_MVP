package org.dagger.mvp.ui.login.view

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun openRegionFragment()
    fun showError(s: String)
    fun fastHideProgress()
}