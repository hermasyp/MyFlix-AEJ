package com.catnip.profile.data.network.services

import com.catnip.profile.data.network.model.request.UpdateProfileRequest
import com.catnip.profile.data.network.model.response.UpdateProfileResponse
import com.catnip.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.PUT

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProfileFeatureApi {

    @PUT("/api/v1/user/")
    suspend fun updateProfile(@Body updateProfileRequest: UpdateProfileRequest): BaseResponse<UpdateProfileResponse>
}