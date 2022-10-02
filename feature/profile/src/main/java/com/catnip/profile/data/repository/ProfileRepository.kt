package com.catnip.profile.data.repository

import com.catnip.core.wrapper.DataResource
import com.catnip.profile.data.network.datasource.ProfileDataSource
import com.catnip.profile.data.network.model.request.UpdateProfileRequest
import com.catnip.profile.data.network.model.response.UpdateProfileResponse
import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
typealias UpdateUserDataResource = DataResource<BaseResponse<UpdateProfileResponse>>

interface ProfileRepository {
    suspend fun updateUser(
        username: String
    ): Flow<UpdateUserDataResource>
}

class ProfileRepositoryImpl(private val dataSource: ProfileDataSource) :
    ProfileRepository, Repository() {
    override suspend fun updateUser(
        username: String
    ): Flow<UpdateUserDataResource> {
        return flow {
            emit(safeNetworkCall {
                dataSource.updateUser(
                    UpdateProfileRequest(
                        username
                    )
                )
            })
        }
    }
}