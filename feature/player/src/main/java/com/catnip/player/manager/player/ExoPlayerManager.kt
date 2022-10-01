package com.catnip.player.manager.player

import android.os.Build
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlin.math.max

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ExoPlayerManager(private val playerView: StyledPlayerView) : PlayerManager {
    //player
    private var player: ExoPlayer? = null

    //state
    private var startAutoPlay = false
    private var startItemIndex = 0
    private var startPosition: Long = 0

    //item
    private var currentMediaItem: MediaItem? = null

    private fun releasePlayer() {
        player?.let {
            updateIndex()
            it.release()
            player = null
            playerView.player = null
        }
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(playerView.context).build().also {
            playerView.player = it
        }
        //seek when there's current position when back
        val haveStartPosition = startItemIndex != C.INDEX_UNSET
        if (haveStartPosition) {
            player?.seekTo(startItemIndex, startPosition)
        }
        play(currentMediaItem, haveStartPosition)
    }


    private fun updateIndex() {
        player?.let {
            startAutoPlay = it.playWhenReady
            startItemIndex = it.currentMediaItemIndex
            startPosition = max(0, it.contentPosition)
        }
    }

    private fun clearIndex() {
        startAutoPlay = true
        startItemIndex = C.INDEX_UNSET
        startPosition = C.TIME_UNSET
    }

    override fun play(videoUrl: String) {
        play(MediaItem.Builder().setUri(videoUrl).build(), false)
    }

    private fun play(mediaItem: MediaItem?, haveStartPosition: Boolean = false) {
        currentMediaItem = mediaItem
        currentMediaItem?.let {
            player?.playWhenReady = startAutoPlay
            player?.setMediaItem(it, !haveStartPosition)
            player?.prepare()
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        clearIndex()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        if (Build.VERSION.SDK_INT > 23) {
            initializePlayer()
            playerView.onResume()
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        if (Build.VERSION.SDK_INT > 23) {
            playerView.onPause()
            releasePlayer()
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        if (Build.VERSION.SDK_INT <= 23) {
            playerView.onPause()
            releasePlayer()
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (Build.VERSION.SDK_INT <= 23 || player == null) {
            initializePlayer()
            playerView.onResume()
        }
    }


}