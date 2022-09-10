package com.catnip.login.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.exception.FieldErrorException
import com.catnip.core.wrapper.ViewResource
import com.catnip.login.R
import com.catnip.login.constants.LoginFieldConstants.FIELD_EMAIL
import com.catnip.login.constants.LoginFieldConstants.FIELD_PASSWORD
import com.catnip.shared.utils.StringUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
typealias CheckFieldLoginResult = List<Pair<Int, Int>>

class CheckLoginFieldUseCase(
    dispatcher: CoroutineDispatcher
) : BaseUseCase<LoginUserUseCase.Param, CheckFieldLoginResult>(dispatcher) {


    override suspend fun execute(param: LoginUserUseCase.Param?): Flow<ViewResource<CheckFieldLoginResult>> =
        flow {
            param?.let { p ->
                val result = mutableListOf<Pair<Int, Int>>()
                checkIsEmailValid(p.email)?.let {
                    result.add(it)
                }
                checkIsPasswordValid(p.password)?.let {
                    result.add(it)
                }
                if (result.isEmpty()) {
                    emit(ViewResource.Success(result))
                } else {
                    emit(ViewResource.Error(FieldErrorException(result)))
                }

            } ?: throw IllegalStateException("Param Required")
        }

    private fun checkIsPasswordValid(password: String): Pair<Int, Int>? {
        return if (password.isEmpty()) {
            Pair(FIELD_PASSWORD, R.string.error_field_password)
        } else {
            null
        }
    }

    private fun checkIsEmailValid(email: String): Pair<Int, Int>? {
        return if (email.isEmpty()) {
            Pair(FIELD_EMAIL, R.string.error_field_email)
        } else if (!StringUtils.isEmailValid(email)) {
            Pair(FIELD_EMAIL, R.string.error_field_email_not_valid)
        } else {
            null

        }

    }


}