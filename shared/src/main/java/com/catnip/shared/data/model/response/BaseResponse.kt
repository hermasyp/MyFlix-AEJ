package com.catnip.shared.data.model.response

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class BaseResponse<D>(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("success")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: D?,
)
