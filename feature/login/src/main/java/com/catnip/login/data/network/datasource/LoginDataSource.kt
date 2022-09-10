package com.catnip.login.data.network.datasource

import com.catnip.login.data.network.model.request.LoginRequest
import com.catnip.login.data.network.service.LoginFeatureApi
import com.catnip.shared.data.model.response.AuthResponse
import com.catnip.shared.data.model.response.BaseResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface LoginDataSource {
    suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<AuthResponse>
}

class LoginDataSourceImpl(private val api: LoginFeatureApi) : LoginDataSource {
    override suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<AuthResponse> {
        return api.loginUser(loginRequest)
    }
}