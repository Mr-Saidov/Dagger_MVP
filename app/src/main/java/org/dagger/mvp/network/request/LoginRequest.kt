package org.dagger.mvp.network.request


data class LoginRequest(
    var username: String,
    var password: String
)