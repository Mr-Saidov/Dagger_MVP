package org.dagger.mvp.ui.branch.view

import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.widget.Toast
import com.ost.avtomaktab.ui.regions.view.branchs.view.BranchAdapter
import kotlinx.android.synthetic.main.fragment_branch.*
import org.dagger.mvp.R
import org.dagger.mvp.di.components.DaggerFragmentComponent
import org.dagger.mvp.di.modules.FragmentModule
import org.dagger.mvp.model.Branch
import org.dagger.mvp.ui.base.BaseFragment
import org.dagger.mvp.ui.branch.presenter.BranchPresenter
import org.dagger.mvp.util.MyAnimationUtil
import org.dagger.mvp.util.setInvisible
import org.dagger.mvp.util.setVisible
import javax.inject.Inject

class BranchFragment : BaseFragment(R.layout.fragment_branch), BranchView, (Branch) -> Unit {
    @Inject
    lateinit var presenter: BranchPresenter

    override fun injectDependency() {
        DaggerFragmentComponent
            .builder()
            .fragmentModule(FragmentModule())
            .build()
            .inject(this)
    }

    override fun onViewCreate() {
        presenter.attachView(this)
        presenter.loadBranchs(arguments!!.getInt("id"))
        modifyTitle()
        ivBack.setOnClickListener { closeActiveFragment() }
    }

    fun modifyTitle() {
        tvTitle.apply {
            text = arguments!!.getString("nameUz")
            ellipsize = TextUtils.TruncateAt.MARQUEE
            isFocusable = true
            isFocusableInTouchMode = true
            requestFocus()
            setSingleLine(true)
            isSelected = true
            marqueeRepeatLimit = -1
        }
    }

    override fun showBranchs(data: ArrayList<Branch>) {

        val adapter = BranchAdapter(data)
        adapter.listener = this
        rvBranch?.adapter = adapter
        rvBranch?.layoutAnimation =
            MyAnimationUtil.getLayoutAnimation(rvBranch.context, MyAnimationUtil.TYPE_SLIDE_RIGHT)
    }

    override fun showError(s: String) {
        context?.let { Toast.makeText(it, s, Toast.LENGTH_LONG).show() }
    }

    override fun showProgress() {
        pbBranch.setVisible()
    }

    override fun hideProgress() {
        pbBranch.setInvisible()
    }

    override fun invoke(branch: Branch) {
        startActivity(
            Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:" + branch.phone)
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    override fun unSubscribePresenter() {
        presenter.unSubscribe()
    }
}