package com.catnip.home.data.network.datasource

import com.catnip.home.data.network.model.response.HomeFeedsResponse
import com.catnip.home.data.network.services.HomeFeatureApi
import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.model.response.MovieResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface HomeDataSource {
    suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse>

    suspend fun fetchWatchlist(): BaseResponse<List<MovieResponse>>
}

class HomeDataSourceImpl(private val api: HomeFeatureApi) : HomeDataSource {
    override suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse> {
        return api.fetchHomeFeeds()
    }

    override suspend fun fetchWatchlist(): BaseResponse<List<MovieResponse>> {
        return api.fetchWatchlist()
    }

}