package com.catnip.detailmovie.presentation.ui.movieinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.shared.delegates.AddOrRemoveWatchlistDelegates
import com.catnip.shared.delegates.AddOrRemoveWatchlistDelegatesImpl

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MovieInfoViewModel : ViewModel(),
    AddOrRemoveWatchlistDelegates by AddOrRemoveWatchlistDelegatesImpl() {
    init {
        init(viewModelScope)
    }
}