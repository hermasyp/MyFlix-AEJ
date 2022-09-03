package com.catnip.shared.utils

import android.text.TextUtils
import android.util.Patterns

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object StringUtils {
    fun isEmailValid(input : CharSequence?) : Boolean{
        return if(TextUtils.isEmpty(input)){
            false
        }else{
            Patterns.EMAIL_ADDRESS.matcher(input).matches()
        }
    }
}