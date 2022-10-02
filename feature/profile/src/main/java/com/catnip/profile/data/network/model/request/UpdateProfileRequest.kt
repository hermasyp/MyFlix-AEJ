package com.catnip.profile.data.network.model.request

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class UpdateProfileRequest(
    @SerializedName("username")
    val username: String
)
