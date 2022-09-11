package com.catnip.register.data.network.services

import com.catnip.register.data.network.model.request.RegisterRequest
import com.catnip.shared.data.model.response.AuthResponse
import com.catnip.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface RegisterFeatureApi {
    @POST("/api/v1/user/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): BaseResponse<AuthResponse>
}