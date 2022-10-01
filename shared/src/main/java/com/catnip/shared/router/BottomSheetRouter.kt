package com.catnip.shared.router

import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface BottomSheetRouter {
    fun createMovieInfoBottomSheet(movieViewParam: MovieViewParam): BottomSheetDialogFragment
}