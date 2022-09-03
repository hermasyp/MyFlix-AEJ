package com.catnip.splashscreen.data.repository

import com.catnip.core.wrapper.DataResource
import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.repository.Repository
import com.catnip.splashscreen.data.network.datasource.SplashScreenDataSource
import com.catnip.splashscreen.data.network.model.response.SyncResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

typealias SyncDataResponse = DataResource<BaseResponse<SyncResponse>>

interface SplashScreenRepository {
    suspend fun doUserSync(): Flow<SyncDataResponse>
}

class SplashScreenRepositoryImpl(val dataSource: SplashScreenDataSource) : SplashScreenRepository,
    Repository() {
    override suspend fun doUserSync(): Flow<SyncDataResponse> {
        return flow {
            emit(safeNetworkCall { dataSource.doUserSync() })
        }
    }
}