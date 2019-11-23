package org.dagger.mvp.ui.region.view

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_region.*
import org.dagger.mvp.R
import org.dagger.mvp.di.components.DaggerFragmentComponent
import org.dagger.mvp.di.modules.FragmentModule
import org.dagger.mvp.model.Region
import org.dagger.mvp.ui.base.BaseFragment
import org.dagger.mvp.ui.branch.view.BranchFragment
import org.dagger.mvp.ui.region.presenter.RegionPresenter
import org.dagger.mvp.util.MyAnimationUtil
import org.dagger.mvp.util.setInvisible
import org.dagger.mvp.util.setVisible
import java.util.*
import javax.inject.Inject

class RegionFragment : BaseFragment(R.layout.fragment_region), RegionView, (Region) -> Unit {

    @Inject
    lateinit var regionPresenter: RegionPresenter

    override fun injectDependency() {
        DaggerFragmentComponent
            .builder()
            .fragmentModule(FragmentModule())
            .build()
            .inject(this)
    }

    override fun onViewCreate() {
        regionPresenter.attachView(this)
        regionPresenter.loadRegions()
        ivBack.setOnClickListener { closeActiveFragment() }
    }

    override fun showRegions(data: ArrayList<Region>) {
        val adapter = RegionAdapter(data)
        adapter.listener = this
        rvRegion.adapter = adapter
        rvRegion.layoutAnimation =
            MyAnimationUtil.getLayoutAnimation(rvRegion.context, MyAnimationUtil.TYPE_SLIDE_RIGHT)
        rvRegion.scheduleLayoutAnimation()
    }

    override fun hideProgress() {
        pbRegion.setInvisible()
    }

    override fun showProgress() {
        pbRegion.setVisible()
    }

    override fun showError(s: String) {
        context?.let { Toast.makeText(it, s, Toast.LENGTH_LONG).show() }
    }

    override fun invoke(region: Region) {
        val bundle = Bundle()
        bundle.putString("nameUz", region.nameUz)
        bundle.putString("nameRu", region.nameRu)
        bundle.putInt("id", region.id)
        val branchFragment = BranchFragment()
        branchFragment.arguments = bundle
        openFragment(branchFragment)
    }

    override fun unSubscribePresenter() {
        regionPresenter.unSubscribe()
    }
}