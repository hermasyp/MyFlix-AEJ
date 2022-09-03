package com.catnip.splashscreen.data.network.service

import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.splashscreen.data.network.model.response.SyncResponse
import retrofit2.http.GET

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface SplashScreenFeatureApi {
    @GET("/api/v1/sync")
    suspend fun doUserSync() : BaseResponse<SyncResponse>
}