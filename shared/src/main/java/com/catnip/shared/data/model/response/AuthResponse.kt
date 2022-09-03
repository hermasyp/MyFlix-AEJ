package com.catnip.shared.data.model.response

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class AuthResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("token_type")
    val tokenType: String?,
    @SerializedName("user")
    val user: UserResponse?
)
