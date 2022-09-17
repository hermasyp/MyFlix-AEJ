package com.catnip.home.data.network.repository

import com.catnip.core.wrapper.DataResource
import com.catnip.home.data.network.datasource.HomeDataSource
import com.catnip.home.data.network.model.response.HomeFeedsResponse
import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.model.response.MovieResponse
import com.catnip.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
typealias HomeDataResource = DataResource<BaseResponse<HomeFeedsResponse>>
typealias WatchlistDataResource = DataResource<BaseResponse<List<MovieResponse>>>

interface HomeRepository {

    suspend fun fetchHomeFeeds(): Flow<HomeDataResource>

    suspend fun fetchWatchlist(): Flow<WatchlistDataResource>
}

class HomeRepositoryImpl(private val dataSource: HomeDataSource) : Repository(), HomeRepository {
    override suspend fun fetchHomeFeeds(): Flow<HomeDataResource> = flow {
        emit(safeNetworkCall { dataSource.fetchHomeFeeds() })
    }

    override suspend fun fetchWatchlist(): Flow<WatchlistDataResource> = flow {
        emit(safeNetworkCall { dataSource.fetchWatchlist() })
    }

}