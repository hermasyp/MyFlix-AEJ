package com.catnip.shared.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.wrapper.DataResource
import com.catnip.core.wrapper.ViewResource
import com.catnip.shared.data.model.mapper.UserMapper
import com.catnip.shared.data.model.viewparam.UserViewParam
import com.catnip.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class GetCurrentUserUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, UserViewParam>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<UserViewParam>> =
        repository.getCurrentUser().map {
            when (it) {
                is DataResource.Success -> {
                    ViewResource.Success(UserMapper.toViewParam(it.payload))
                }
                is DataResource.Error -> {
                    ViewResource.Error(it.exception)
                }
            }
        }
}