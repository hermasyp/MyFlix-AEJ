package com.catnip.myflix.router

import com.catnip.detailmovie.presentation.ui.movieinfo.MovieInfoBottomSheet
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.shared.router.BottomSheetRouter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class BottomSheetRouterImpl : BottomSheetRouter {
    override fun createMovieInfoBottomSheet(movieViewParam: MovieViewParam): BottomSheetDialogFragment {
        return MovieInfoBottomSheet(movieViewParam)
    }
}