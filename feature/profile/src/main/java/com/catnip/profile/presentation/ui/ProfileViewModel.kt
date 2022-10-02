package com.catnip.profile.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.core.wrapper.ViewResource
import com.catnip.profile.domain.UpdateProfileUseCase
import com.catnip.shared.data.model.viewparam.UserViewParam
import com.catnip.shared.domain.ClearUserDataUseCase
import com.catnip.shared.domain.GetCurrentUserUseCase
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ProfileViewModel(
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val clearUserDataUseCase: ClearUserDataUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    val updateProfileResult = MutableLiveData<ViewResource<UserViewParam?>>()
    val logoutResult = MutableLiveData<ViewResource<Boolean>>()
    val currentUserResult = MutableLiveData<ViewResource<UserViewParam>>()

    fun updateProfileData(username: String) {
        viewModelScope.launch {
            updateProfileUseCase(UpdateProfileUseCase.UpdateProfileParam(username = username))
                .collect {
                    updateProfileResult.postValue(it)
                }
        }
    }

    fun doLogout() {
        viewModelScope.launch {
            clearUserDataUseCase().collect {
                logoutResult.postValue(it)
            }
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect {
                currentUserResult.postValue(it)
            }
        }
    }
}