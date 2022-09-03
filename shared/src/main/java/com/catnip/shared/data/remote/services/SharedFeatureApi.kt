package com.catnip.shared.data.remote.services

import com.catnip.shared.data.model.request.WatchlistRequest
import com.catnip.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.POST

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface SharedFeatureApi {

    @POST("/api/v1/watchlist")
    suspend fun addWatchlist(@Body request: WatchlistRequest): BaseResponse<Any>

    @HTTP(method = "DELETE", path = "/api/v1/watchlist", hasBody = true)
    suspend fun removeWatchlist(@Body request: WatchlistRequest): BaseResponse<Any>

}