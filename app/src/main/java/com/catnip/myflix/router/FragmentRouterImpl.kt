package com.catnip.myflix.router

import androidx.fragment.app.Fragment
import com.catnip.player.presentation.playerfragment.PlayerFragment
import com.catnip.shared.router.FragmentRouter

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FragmentRouterImpl : FragmentRouter {
    override fun createPlayerFragment(videoUrl: String): Fragment {
        return PlayerFragment.newInstance(videoUrl)
    }
}