package com.catnip.detailmovie.presentation.ui.detailmovie

import android.content.Context
import android.content.Intent
import androidx.core.view.isVisible
import coil.load
import com.catnip.core.base.BaseActivity
import com.catnip.detailmovie.databinding.ActivityDetailMovieBinding
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.shared.router.ActivityRouter
import com.catnip.shared.utils.CommonUtils
import com.catnip.shared.utils.ext.subscribe
import org.koin.android.ext.android.inject

class DetailMovieActivity :
    BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel>(ActivityDetailMovieBinding::inflate) {

    override val viewModel: DetailMovieViewModel by inject()

    private val activityRouter: ActivityRouter by inject()

    private val movieId: String? by lazy { intent?.extras?.getString(EXTRA_ID_MOVIE) }

    companion object {
        private const val EXTRA_ID_MOVIE = "EXTRA_ID_MOVIE"
        fun createIntent(context: Context, movieId: String): Intent {
            return Intent(context, DetailMovieActivity::class.java).apply {
                putExtra(EXTRA_ID_MOVIE, movieId)
            }
        }
    }

    override fun initView() {
        setupToolbar()
        movieId?.let {
            viewModel.fetchDetail(it)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        enableHomeAsBack()
    }


    override fun observeData() {
        viewModel.detailMovieResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    showLoading(false)
                    result.payload?.let { movie -> bindMovie(movie) }
                },
                doOnLoading = {
                    showLoading(true)
                },
                doOnError = { error ->
                    error.exception?.let { e -> showError(true, e) }
                }
            )
        }
        viewModel.getWatchlistResult().observe(this) {
            it.subscribe(
                doOnSuccess = {
                    movieId?.let { id -> viewModel.fetchDetail(id) }
                },
                doOnError = { error ->
                    error.exception?.let { e -> showError(true, e) }
                }
            )
        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbDetail.isVisible = isShowLoading
        binding.layoutDetail.layoutDetail.isVisible = !isShowLoading
    }

    private fun bindMovie(movie: MovieViewParam) {
        setClickListener(movie)
        loadPoster(movie.posterUrl)
        loadInfoMovie(movie)
    }

    private fun loadInfoMovie(movie: MovieViewParam) {
        binding.layoutDetail.clDetailMovie.ivWatchlist.setImageResource(
            CommonUtils.getWatchlistIcon(
                movie.isUserWatchlist
            )
        )
        binding.layoutDetail.clDetailMovie.tvTitleMovie.text = movie.title
        binding.layoutDetail.clDetailMovie.tvMovieDesc.text = movie.overview
        binding.layoutDetail.clDetailMovie.tvAdditionalInfo.text =
            "${movie.releaseDate} • ${movie.filmRate} • ${movie.runtime}m "
    }

    private fun loadPoster(url: String) {
        binding.layoutDetail.layoutHeaderDetail.ivPosterDetail.load(url)
    }

    private fun setClickListener(movie: MovieViewParam) {
        binding.layoutDetail.layoutHeaderDetail.ivPlayTrailer.setOnClickListener {
            binding.layoutDetail.layoutHeaderDetail.flHeaderPoster.isVisible = false
            binding.layoutDetail.layoutHeaderDetail.containerPlayer.isVisible = true
            //todo : replace container into player
        }
        binding.layoutDetail.clDetailMovie.llShare.setOnClickListener {
            CommonUtils.shareFilm(this, movie)
        }
        binding.layoutDetail.clDetailMovie.llMyList.setOnClickListener {
            viewModel.addOrRemoveWatchlist(movie)
        }
        binding.layoutDetail.clDetailMovie.cvPlay.setOnClickListener {
            //todo : navigate to player activity

        }
    }

}