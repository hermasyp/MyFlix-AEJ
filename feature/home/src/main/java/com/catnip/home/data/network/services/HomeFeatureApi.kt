package com.catnip.home.data.network.services

import com.catnip.home.data.network.model.response.HomeFeedsResponse
import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.model.response.MovieResponse
import retrofit2.http.GET

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface HomeFeatureApi {

    @GET("api/v1/homepage")
    suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse>

    @GET("api/v1/watchlist")
    suspend fun fetchWatchlist(): BaseResponse<List<MovieResponse>>

}