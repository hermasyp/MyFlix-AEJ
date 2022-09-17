package com.catnip.home.presentation.viewparam

import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class SectionViewParam(
    @SerializedName("section_id")
    val sectionId: Int,
    @SerializedName("section_name")
    val sectionName: String,
    @SerializedName("contents")
    val contents: List<MovieViewParam>
)
