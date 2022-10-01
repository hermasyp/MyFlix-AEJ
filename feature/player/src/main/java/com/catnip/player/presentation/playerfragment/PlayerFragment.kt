package com.catnip.player.presentation.playerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.catnip.player.databinding.FragmentPlayerBinding
import com.catnip.player.manager.player.ExoPlayerManager
import com.catnip.player.manager.player.PlayerManager


class PlayerFragment : Fragment() {
    private val videoUrl: String? by lazy {
        arguments?.getString(ARG_VIDEO_URL)
    }
    private lateinit var binding: FragmentPlayerBinding
    private lateinit var playerManager: PlayerManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerManager = ExoPlayerManager(binding.playerView)
        this.lifecycle.addObserver(playerManager)
        videoUrl?.let { playerManager.play(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.lifecycle.removeObserver(playerManager)
    }

    companion object {
        private const val ARG_VIDEO_URL = "ARG_VIDEO_URL"

        @JvmStatic
        fun newInstance(videoUrl: String) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_VIDEO_URL, videoUrl)
                }
            }
    }
}