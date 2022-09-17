package com.catnip.home.presentation.viewparam

import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class HomeFeedsViewParam(
    @SerializedName("header")
    val header: MovieViewParam,
    @SerializedName("sections")
    val sections: List<SectionViewParam>,
)
