package com.catnip.profile.data.network.datasource

import com.catnip.profile.data.network.model.request.UpdateProfileRequest
import com.catnip.profile.data.network.model.response.UpdateProfileResponse
import com.catnip.profile.data.network.services.ProfileFeatureApi
import com.catnip.shared.data.model.response.BaseResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

interface ProfileDataSource {
    suspend fun updateUser(updateProfileRequest: UpdateProfileRequest): BaseResponse<UpdateProfileResponse>
}

class ProfileDataSourceImpl(
    private val services: ProfileFeatureApi
) : ProfileDataSource {
    override suspend fun updateUser(updateProfileRequest: UpdateProfileRequest): BaseResponse<UpdateProfileResponse> {
        return services.updateProfile(updateProfileRequest)
    }
}