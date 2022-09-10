package com.catnip.shared.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.wrapper.DataResource
import com.catnip.core.wrapper.ViewResource
import com.catnip.shared.data.model.response.UserResponse
import com.catnip.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class SaveAuthDataUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<SaveAuthDataUseCase.Param, Boolean>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<Boolean>> = flow {
        param?.let {
            val saveLoginStatus = repository.updateUserLoginStatus(param.isLoggedIn).first()
            val saveToken = repository.updateUserToken(param.authToken).first()
            val saveUser = repository.setCurrentUser(param.user).first()

            if (saveUser is DataResource.Success && saveToken is DataResource.Success && saveLoginStatus is DataResource.Success) {
                emit(ViewResource.Success(true))
            } else {
                emit(ViewResource.Error(IllegalStateException("Failed to save local data")))
            }
        }
    }

    data class Param(val isLoggedIn: Boolean, val authToken: String, val user: UserResponse)
}