package com.catnip.home.data.network.model.response

import com.catnip.shared.data.model.response.MovieResponse
import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class HomeFeedsResponse(
    @SerializedName("header")
    val header: MovieResponse?,
    @SerializedName("sections")
    val sections: List<SectionResponse>?,
)
