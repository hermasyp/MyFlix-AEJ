package com.catnip.detailmovie.presentation.ui.movieinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.shared.router.ActivityRouter
import com.catnip.shared.utils.CommonUtils
import com.catnip.shared.utils.ext.subscribe
import com.catnip.styling.databinding.BottomSheetMovieInfoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MovieInfoBottomSheet(private val movie: MovieViewParam) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetMovieInfoBinding
    private val activityRouter: ActivityRouter by inject()
    private val viewModel: MovieInfoViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetMovieInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindMovie(movie)
        observeData()
    }

    private fun bindMovie(movie: MovieViewParam) {
        setClickListener(movie)
        loadPoster(movie.posterUrl)
        loadInfoMovie(movie)
    }

    private fun loadInfoMovie(movie: MovieViewParam) {
        binding.tvMovieTitle.text = movie.title
        binding.tvShortDesc.text = movie.overview
        binding.tvAdditionalInfo.text =
            "${movie.releaseDate} • ${movie.filmRate} • ${movie.runtime}m "
        binding.ivWatchlist.setImageResource(CommonUtils.getWatchlistIcon(movie.isUserWatchlist))
    }

    private fun loadPoster(url: String) {
        binding.ivPoster.load(url)
    }

    private fun setClickListener(movie: MovieViewParam) {
        binding.ivClose.setOnClickListener {
            this.dismiss()
        }
        binding.llPlayMovie.setOnClickListener {
            //todo : open player activity
        }
        binding.llShare.setOnClickListener {
            CommonUtils.shareFilm(requireContext(), movie)
        }
        binding.llMyList.setOnClickListener {
            viewModel.addOrRemoveWatchlist(movie)
        }
        binding.tvDetailMovie.setOnClickListener {
            startActivity(
                activityRouter.detailMovieActivity(
                    requireContext(),
                    movie.id.toString()
                )
            )
        }
    }

    private fun observeData() {
        viewModel.getWatchlistResult().observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    result.payload?.let { movie ->
                        movie.isUserWatchlist = movie.isUserWatchlist
                        binding.ivWatchlist.setImageResource(CommonUtils.getWatchlistIcon(movie.isUserWatchlist))
                    }
                },
                doOnError = { error ->
                    Toast.makeText(requireContext(), error.message.orEmpty(), Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}