package com.catnip.profile.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.exception.FieldErrorException
import com.catnip.core.wrapper.ViewResource
import com.catnip.profile.R
import com.catnip.profile.constants.UpdateProfileFieldConstant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
typealias CheckFieldUpdateProfileResult = List<Pair<Int, Int>>


class CheckProfileUpdateFieldUseCase(dispatcher: CoroutineDispatcher) :
    BaseUseCase<UpdateProfileUseCase.UpdateProfileParam, CheckFieldUpdateProfileResult>(dispatcher) {

    override suspend fun execute(param: UpdateProfileUseCase.UpdateProfileParam?): Flow<ViewResource<CheckFieldUpdateProfileResult>> {
        return flow {
            param?.let {
                val result = mutableListOf<Pair<Int, Int>>()
                checkIsUsernameValid(param.username)?.let {
                    result.add(it)
                }
                if (result.isEmpty()) {
                    emit(ViewResource.Success(result))
                } else {
                    emit(ViewResource.Error(FieldErrorException(result)))
                }
            } ?: throw IllegalStateException("Param Required")
        }
    }

    private fun checkIsUsernameValid(password: String): Pair<Int, Int>? {
        return if (password.length < 8) {
            Pair(UpdateProfileFieldConstant.USERNAME_FIELD, R.string.error_text_username_less_than_min_char)
        } else if (password.isEmpty()) {
            Pair(UpdateProfileFieldConstant.USERNAME_FIELD, R.string.error_text_username_required)
        } else if (password.contains(" ")) {
            Pair(UpdateProfileFieldConstant.USERNAME_FIELD, R.string.error_text_username_no_space)
        } else {
            null
        }
    }
}