package com.catnip.register.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.core.wrapper.ViewResource
import com.catnip.register.domain.RegisterUserUseCase
import com.catnip.shared.data.model.viewparam.UserViewParam
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class RegisterViewModel(private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {

    val registerResult = MutableLiveData<ViewResource<UserViewParam?>>()

    fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ) {
        viewModelScope.launch {
            registerUserUseCase(
                RegisterUserUseCase.RegisterParam(
                    email = email,
                    password = password,
                    gender = gender,
                    birthdate = birthdate,
                    username = username
                )
            ).collect {
                registerResult.postValue(it)
            }
        }
    }

}