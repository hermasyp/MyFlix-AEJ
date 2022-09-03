package com.catnip.shared.utils

import android.graphics.Color

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object ColorUtils {
    fun changeAlpha(currentColor: Int, fraction: Double): Int {
        val red: Int = Color.red(currentColor)
        val green: Int = Color.green(currentColor)
        val blue: Int = Color.blue(currentColor)
        val alpha: Int = (Color.alpha(currentColor) * (fraction)).toInt()
        return Color.argb(alpha, red, green, blue)
    }
}