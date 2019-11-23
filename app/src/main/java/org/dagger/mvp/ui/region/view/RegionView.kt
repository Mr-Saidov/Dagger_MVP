package org.dagger.mvp.ui.region.view

import org.dagger.mvp.model.Region
import java.util.*

interface RegionView {
    fun showProgress()
    fun hideProgress()
    fun showRegions(data: ArrayList<Region>)
    fun showError(s: String)
}