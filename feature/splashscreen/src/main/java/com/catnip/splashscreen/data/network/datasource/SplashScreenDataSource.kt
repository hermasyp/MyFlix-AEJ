package com.catnip.splashscreen.data.network.datasource

import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.splashscreen.data.network.model.response.SyncResponse
import com.catnip.splashscreen.data.network.service.SplashScreenFeatureApi

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface SplashScreenDataSource {
    suspend fun doUserSync(): BaseResponse<SyncResponse>
}

class SplashScreenDataSourceImpl(val service: SplashScreenFeatureApi) : SplashScreenDataSource {
    override suspend fun doUserSync(): BaseResponse<SyncResponse> {
        return service.doUserSync()
    }
}