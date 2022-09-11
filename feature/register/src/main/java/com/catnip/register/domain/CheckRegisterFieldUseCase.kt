package com.catnip.register.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.exception.FieldErrorException
import com.catnip.core.wrapper.ViewResource
import com.catnip.register.R
import com.catnip.register.constants.RegisterFieldConstants
import com.catnip.shared.utils.StringUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
typealias CheckFieldRegisterResult = List<Pair<Int, Int>>

class CheckRegisterFieldUseCase(
    dispatcher: CoroutineDispatcher
) : BaseUseCase<RegisterUserUseCase.RegisterParam, CheckFieldRegisterResult>(dispatcher) {

    override suspend fun execute(param: RegisterUserUseCase.RegisterParam?): Flow<ViewResource<CheckFieldRegisterResult>> =
        flow {
            param?.let { p ->
                val result = mutableListOf<Pair<Int, Int>>()
                checkIsEmailValid(p.email)?.let {
                    result.add(it)
                }
                checkIsPasswordValid(p.password)?.let {
                    result.add(it)
                }
                checkIsBirthdateValid(p.birthdate)?.let {
                    result.add(it)
                }
                checkIsUsernameValid(p.username)?.let {
                    result.add(it)
                }
                checkIsGenderValid(p.gender)?.let {
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
            Pair(RegisterFieldConstants.PASSWORD_FIELD, R.string.error_field_empty)
        } else if (password.length < 8) {
            Pair(
                RegisterFieldConstants.PASSWORD_FIELD,
                R.string.error_field_password_length_below_min
            )
        }else {
            null
        }
    }

    private fun checkIsEmailValid(email: String): Pair<Int, Int>? {
        return if (email.isEmpty()) {
            Pair(RegisterFieldConstants.EMAIL_FIELD, R.string.error_field_empty)
        } else if (!StringUtils.isEmailValid(email)) {
            Pair(RegisterFieldConstants.EMAIL_FIELD, R.string.error_field_email_not_valid)
        } else {
            null
        }
    }

    private fun checkIsUsernameValid(username: String): Pair<Int, Int>? {
        return if (username.isEmpty()) {
            Pair(RegisterFieldConstants.USERNAME_FIELD, R.string.error_field_empty)
        } else if (username.length < 8) {
            Pair(
                RegisterFieldConstants.USERNAME_FIELD,
                R.string.error_field_username_length_below_min
            )
        } else if (username.contains(" ")) {
            Pair(
                RegisterFieldConstants.USERNAME_FIELD,
                R.string.error_field_username_not_allowed_character
            )
        } else {
            null
        }
    }

    private fun checkIsGenderValid(gender: String): Pair<Int, Int>? {
        return if (gender.isEmpty()) {
            Pair(RegisterFieldConstants.GENDER_FIELD, R.string.error_field_empty)
        } else {
            null
        }
    }

    private fun checkIsBirthdateValid(birthdate: String): Pair<Int, Int>? {
        return if (birthdate.isEmpty()) {
            Pair(RegisterFieldConstants.BIRTHDATE_FIELD, R.string.error_field_empty)
        } else {
            null
        }
    }
}