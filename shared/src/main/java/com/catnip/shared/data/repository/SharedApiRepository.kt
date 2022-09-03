package com.catnip.shared.data.repository

import com.catnip.core.wrapper.DataResource
import com.catnip.shared.data.model.request.WatchlistRequest
import com.catnip.shared.data.remote.datasource.SharedFeatureApiDataSource
import com.catnip.shared.data.remote.services.SharedFeatureApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface SharedApiRepository {
    suspend fun addWatchlist(movieId: String): Flow<DataResource<Any>>

    suspend fun removeWatchlist(movieId: String): Flow<DataResource<Any>>
}

class SharedApiRepositoryImpl(private val dataSource: SharedFeatureApiDataSource) : Repository(),
    SharedApiRepository {

    override suspend fun addWatchlist(movieId: String): Flow<DataResource<Any>> {
        return flow {
            emit(safeNetworkCall { dataSource.addWatchlist(WatchlistRequest(movieId)) })
        }
    }

    override suspend fun removeWatchlist(movieId: String): Flow<DataResource<Any>> {
        return flow {
            emit(safeNetworkCall { dataSource.removeWatchlist(WatchlistRequest(movieId)) })
        }
    }

}