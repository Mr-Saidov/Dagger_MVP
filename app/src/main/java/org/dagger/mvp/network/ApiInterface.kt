package org.dagger.mvp.network

import io.reactivex.Observable
import org.dagger.mvp.network.request.AccessTokenRequest
import org.dagger.mvp.network.request.BranchRequest
import org.dagger.mvp.network.request.LoginRequest
import org.dagger.mvp.network.response.AccessTokenResponse
import org.dagger.mvp.network.response.BranchResponse
import org.dagger.mvp.network.response.LoginResponse
import org.dagger.mvp.network.response.RegionResponse
import org.dagger.mvp.util.Constant.Companion.BASE_URL
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("authorize")
    fun auth(@Body login: LoginRequest): Observable<LoginResponse>

    @POST("accesstoken")
    fun fetchToken(@Body accessRequest: AccessTokenRequest): Observable<AccessTokenResponse>

    @GET("region")
    fun getRegions(): Observable<RegionResponse>

    @POST("get-branch")
    fun getBranchs(@Body branchRequest: BranchRequest): Observable<BranchResponse>

    companion object Factory {
        fun create(): ApiInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}