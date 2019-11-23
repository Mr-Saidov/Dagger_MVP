package org.dagger.mvp.network.request

import com.google.gson.annotations.Expose

data class BranchRequest(
    @Expose
    var region_id: Int
)