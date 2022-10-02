package com.catnip.shared.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.wrapper.ViewResource
import com.catnip.shared.data.model.response.UserResponse
import com.catnip.shared.data.repository.UserPreferenceRepository
import com.catnip.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class SaveUserDataUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<SaveUserDataUseCase.Param, Boolean>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<Boolean>> {
        return flow {
            param?.let {
                repository.setCurrentUser(param.user).collect {
                    it.suspendSubscribe(
                        doOnSuccess = {
                            emit(ViewResource.Success(true))

                        }, doOnError = {
                            emit(ViewResource.Error(IllegalStateException("Failed when save local data")))
                        })
                }
            }
        }
    }

    data class Param(val user: UserResponse)
}