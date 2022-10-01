package com.catnip.shared.router

import androidx.fragment.app.Fragment

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface FragmentRouter {
    fun createPlayerFragment(videoUrl : String) : Fragment
}