package com.catnip.shared.data.remote.datasource
import com.catnip.shared.data.model.request.WatchlistRequest
import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.remote.services.SharedFeatureApi

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface SharedFeatureApiDataSource {
    suspend fun addWatchlist(request: WatchlistRequest): BaseResponse<Any>
    suspend fun removeWatchlist(request: WatchlistRequest): BaseResponse<Any>
}

class SharedFeatureApiDataSourceImpl(private val sharedFeatureApi: SharedFeatureApi) :
    SharedFeatureApiDataSource {
    override suspend fun addWatchlist(request: WatchlistRequest): BaseResponse<Any> {
        return sharedFeatureApi.addWatchlist(request)
    }

    override suspend fun removeWatchlist(request: WatchlistRequest): BaseResponse<Any> {
        return sharedFeatureApi.removeWatchlist(request)
    }

}