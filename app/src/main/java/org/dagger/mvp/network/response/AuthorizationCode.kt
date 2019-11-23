package org.dagger.mvp.network.response

import com.google.gson.annotations.SerializedName

data class AuthorizationCode(

    @SerializedName("authorization_code")
    val authorizationCode: String,

    @SerializedName("expires_at")
    val expiresAt: String
)