package com.catnip.register.data.network.datasource

import com.catnip.register.data.network.model.request.RegisterRequest
import com.catnip.register.data.network.services.RegisterFeatureApi
import com.catnip.shared.data.model.response.AuthResponse
import com.catnip.shared.data.model.response.BaseResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface RegisterDataSource {
    suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<AuthResponse>
}

class RegisterDataSourceImpl(private val api: RegisterFeatureApi) : RegisterDataSource {
    override suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<AuthResponse> {
        return api.registerUser(registerRequest)
    }
}