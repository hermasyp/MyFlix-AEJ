package com.catnip.detailmovie.presentation.ui.detailmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.core.wrapper.ViewResource
import com.catnip.detailmovie.domain.GetDetailMovieUseCase
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.shared.delegates.AddOrRemoveWatchlistDelegates
import com.catnip.shared.delegates.AddOrRemoveWatchlistDelegatesImpl
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class DetailMovieViewModel(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
) : ViewModel(), AddOrRemoveWatchlistDelegates by AddOrRemoveWatchlistDelegatesImpl() {

    init {
        init(viewModelScope)
    }

    val detailMovieResult = MutableLiveData<ViewResource<MovieViewParam>>()

    fun fetchDetail(movieId: String) {
        viewModelScope.launch {
            getDetailMovieUseCase(GetDetailMovieUseCase.Param(movieId)).collect {
                detailMovieResult.postValue(it)
            }
        }
    }

}