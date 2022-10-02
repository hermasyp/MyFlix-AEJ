package com.catnip.shared.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.wrapper.DataResource
import com.catnip.core.wrapper.ViewResource
import com.catnip.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ClearUserDataUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Any, Boolean>(dispatcher) {
    override suspend fun execute(param: Any?): Flow<ViewResource<Boolean>> {
        return repository.clearData().map {
            when (it) {
                is DataResource.Success -> {
                    ViewResource.Success(true)
                }
                else -> {
                    ViewResource.Error(it.exception)
                }
            }
        }
    }
}