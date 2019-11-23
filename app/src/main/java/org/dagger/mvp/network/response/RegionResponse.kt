package org.dagger.mvp.network.response

import org.dagger.mvp.model.Region

data class RegionResponse(
    var status: Int,
    var data: ArrayList<Region>
)