package com.catnip.login.data.repository

import com.catnip.core.wrapper.DataResource
import com.catnip.login.data.network.datasource.LoginDataSource
import com.catnip.login.data.network.model.request.LoginRequest
import com.catnip.shared.data.model.response.AuthResponse
import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
typealias LoginDataResource = DataResource<BaseResponse<AuthResponse>>

interface LoginRepository {
    suspend fun loginUser(
        email: String,
        password: String
    ): Flow<LoginDataResource>
}

class LoginRepositoryImpl(private val dataSource: LoginDataSource) : LoginRepository, Repository() {
    override suspend fun loginUser(email: String, password: String): Flow<LoginDataResource> {
        return flow {
            emit(safeNetworkCall { dataSource.loginUser(LoginRequest(email, password)) })
        }
    }
}