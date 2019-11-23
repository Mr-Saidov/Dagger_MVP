package org.dagger.mvp.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccessTokenRequest(
    @Expose
    @SerializedName("authorization_code")
    val authorizationCode: String
)