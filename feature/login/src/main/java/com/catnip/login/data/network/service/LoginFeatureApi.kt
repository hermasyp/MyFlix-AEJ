package com.catnip.login.data.network.service

import com.catnip.login.data.network.model.request.LoginRequest
import com.catnip.shared.data.model.response.AuthResponse
import com.catnip.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface LoginFeatureApi {
    @POST("/api/v1/user/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): BaseResponse<AuthResponse>
}