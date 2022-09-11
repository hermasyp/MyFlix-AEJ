package com.catnip.register.data.repository

import com.catnip.core.wrapper.DataResource
import com.catnip.register.data.network.datasource.RegisterDataSource
import com.catnip.register.data.network.model.request.RegisterRequest
import com.catnip.shared.data.model.response.AuthResponse
import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

typealias RegisterDataResource = DataResource<BaseResponse<AuthResponse>>

interface RegisterRepository {
    suspend fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ): Flow<RegisterDataResource>
}

class RegisterRepositoryImpl(private val dataSource: RegisterDataSource) : RegisterRepository,
    Repository() {
    override suspend fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ): Flow<RegisterDataResource> {
        return flow {
            emit(safeNetworkCall {
                dataSource.registerUser(
                    RegisterRequest(
                        email = email,
                        username = username,
                        password = password,
                        gender = gender,
                        birthdate = birthdate
                    )
                )
            })
        }
    }

}