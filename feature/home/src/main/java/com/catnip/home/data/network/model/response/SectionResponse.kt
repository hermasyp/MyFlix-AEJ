package com.catnip.home.data.network.model.response

import com.catnip.shared.data.model.response.MovieResponse
import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class SectionResponse(
    @SerializedName("section_id")
    val sectionId: Int?,
    @SerializedName("section_name")
    val sectionName: String?,
    @SerializedName("contents")
    val contents: List<MovieResponse>?)
