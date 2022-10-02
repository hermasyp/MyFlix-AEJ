package com.catnip.profile.data.network.model.response

import com.catnip.shared.data.model.response.UserResponse
import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class UpdateProfileResponse(
    @SerializedName("user")
    val user: UserResponse?
)
