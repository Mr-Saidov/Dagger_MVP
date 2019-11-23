package org.dagger.mvp.network.response

data class LoginResponse(
    val status: Int,
    val data: AuthorizationCode
) {
    override fun toString(): String {
        return "LoginResponse   <---   Status: " + status + ", " + data
    }
}