package org.dagger.mvp.ui.branch.view

import org.dagger.mvp.model.Branch

interface BranchView {
    fun showProgress()
    fun hideProgress()
    fun showBranchs(data: ArrayList<Branch>)
    fun showError(s: String)
}