package com.catnip.profile.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.wrapper.ViewResource
import com.catnip.profile.data.repository.ProfileRepository
import com.catnip.shared.data.model.mapper.UserMapper
import com.catnip.shared.data.model.viewparam.UserViewParam
import com.catnip.shared.domain.SaveUserDataUseCase
import com.catnip.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class UpdateProfileUseCase(
    private val checkFieldUseCase: CheckProfileUpdateFieldUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase,
    private val repository: ProfileRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<UpdateProfileUseCase.UpdateProfileParam, UserViewParam?>(dispatcher) {

    data class UpdateProfileParam(
        val username: String
    )

    override suspend fun execute(param: UpdateProfileParam?): Flow<ViewResource<UserViewParam?>> {
        param?.let {
            return flow {
                emit(ViewResource.Loading())
                checkFieldUseCase(param).first().suspendSubscribe(doOnSuccess = {
                    repository.updateUser(param.username).collect { updateResult ->
                        updateResult.suspendSubscribe(doOnSuccess = {
                            updateResult.payload?.data?.user?.let { user ->
                                saveUserDataUseCase(SaveUserDataUseCase.Param(user)).first()
                                    .suspendSubscribe(
                                        doOnSuccess = {
                                            if (it.payload == true) {
                                                emit(ViewResource.Success(UserMapper.toViewParam(user)))
                                            } else {
                                                emit(ViewResource.Error(IllegalStateException("Save user data failed")))
                                            }
                                        }, doOnError = { error ->
                                            emit(ViewResource.Error(error.exception))
                                        }
                                    )
                            }
                        }, doOnError = { error ->
                            emit(ViewResource.Error(error.exception))
                        })
                    }
                }, doOnError = {
                    emit(ViewResource.Error(it.exception))
                })
            }
        } ?: throw IllegalStateException("Required Param")
    }
}