package org.dagger.mvp.network.response

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("expires_at")
    val expiresAt: String
)