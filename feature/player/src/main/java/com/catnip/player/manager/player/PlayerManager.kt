package com.catnip.player.manager.player

import androidx.lifecycle.DefaultLifecycleObserver

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface PlayerManager : DefaultLifecycleObserver{
    fun play(videoUrl : String)
}