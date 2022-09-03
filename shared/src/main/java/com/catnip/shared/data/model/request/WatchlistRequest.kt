package com.catnip.shared.data.model.request

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class WatchlistRequest(
    @SerializedName("movie_id")
    val movieId: String,
)
