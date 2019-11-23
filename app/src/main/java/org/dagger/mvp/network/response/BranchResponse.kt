package org.dagger.mvp.network.response

import org.dagger.mvp.model.Branch

class BranchResponse(
    var status: Int,
    var data: ArrayList<Branch>
)