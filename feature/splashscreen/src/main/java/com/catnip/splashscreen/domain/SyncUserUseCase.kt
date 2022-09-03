package com.catnip.splashscreen.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.wrapper.ViewResource
import com.catnip.shared.data.model.mapper.UserMapper
import com.catnip.shared.data.model.viewparam.UserViewParam
import com.catnip.shared.data.repository.UserPreferenceRepository
import com.catnip.shared.utils.ext.suspendSubscribe
import com.catnip.splashscreen.data.repository.SplashScreenRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
typealias SyncResult = Pair<Boolean, UserViewParam?>

class SyncUserUseCase(
    private val splashScreenRepository: SplashScreenRepository,
    private val userPreferenceRepository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, SyncResult>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<SyncResult>> {
        return flow {
            userPreferenceRepository.isUserLoggedIn().first().suspendSubscribe(
                doOnSuccess = { result ->
                    if (result.payload == true) {
                        splashScreenRepository.doUserSync().collect {
                            it.suspendSubscribe(
                                doOnSuccess = { response ->
                                    emit(ViewResource.Success(Pair(true, UserMapper.toViewParam(response.payload?.data?.userResponse))))
                                }, doOnError = { error ->
                                    emit(ViewResource.Error(error.exception))
                                })
                        }
                    } else {
                        emit(
                            ViewResource.Success(Pair(false, null))
                        )
                    }
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                })
        }
    }
}