package com.catnip.splashscreen.data.network.model.response

import com.catnip.shared.data.model.response.UserResponse
import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class SyncResponse(
    @SerializedName("user")
    val userResponse: UserResponse?
)
