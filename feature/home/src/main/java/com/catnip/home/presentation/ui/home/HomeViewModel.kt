package com.catnip.home.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.core.wrapper.ViewResource
import com.catnip.home.domain.GetHomeFeedsUseCase
import com.catnip.home.domain.GetUserWatchlistUseCase
import com.catnip.home.presentation.viewparam.homeitem.HomeUiItem
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.shared.data.model.viewparam.UserViewParam
import com.catnip.shared.delegates.AddOrRemoveWatchlistDelegates
import com.catnip.shared.delegates.AddOrRemoveWatchlistDelegatesImpl
import com.catnip.shared.domain.GetCurrentUserUseCase
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class HomeViewModel(
    private val getHomeFeedsUseCase: GetHomeFeedsUseCase,
    private val getUserWatchlistUseCase: GetUserWatchlistUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel(), AddOrRemoveWatchlistDelegates by AddOrRemoveWatchlistDelegatesImpl() {

    val homeFeedsResult = MutableLiveData<ViewResource<List<HomeUiItem>>>()
    val watchlistResult = MutableLiveData<ViewResource<List<MovieViewParam>>>()
    val currentUserResult = MutableLiveData<ViewResource<UserViewParam>>()

    init {
        init(viewModelScope)
    }

    fun fetchHome() {
        viewModelScope.launch {
            getHomeFeedsUseCase().collect {
                homeFeedsResult.postValue(it)
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
    fun fetchWatchlist() {
        viewModelScope.launch {
            getUserWatchlistUseCase().collect {
                watchlistResult.postValue(it)
            }
        }
    }


}