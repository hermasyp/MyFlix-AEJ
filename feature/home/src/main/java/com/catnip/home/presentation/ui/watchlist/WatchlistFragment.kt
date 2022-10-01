package com.catnip.home.presentation.ui.watchlist

import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.catnip.core.base.BaseFragment
import com.catnip.home.R
import com.catnip.home.databinding.FragmentWatchlistBinding
import com.catnip.home.presentation.adapter.MovieAdapter
import com.catnip.home.presentation.ui.home.HomeViewModel
import com.catnip.shared.router.BottomSheetRouter
import com.catnip.shared.utils.ext.subscribe
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WatchlistFragment : BaseFragment<FragmentWatchlistBinding, HomeViewModel>(
    FragmentWatchlistBinding::inflate
) {

    override val viewModel: HomeViewModel by sharedViewModel()
    private val bottomSheetRouter: BottomSheetRouter by inject()


    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(true) {
            bottomSheetRouter.createMovieInfoBottomSheet(it)
                .show(childFragmentManager, null)
        }
    }

    override fun initView() {
        binding.rvWatchlist.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        initRefresh()
        viewModel.fetchWatchlist()
    }

    override fun observeData() {
        super.observeData()
        viewModel.watchlistResult.observe(this) {
            it.subscribe(
                doOnSuccess = { response ->
                    showLoading(false)
                    binding.tvErrorWatchlist.isVisible = false
                    response.payload?.let { data -> movieAdapter.setItems(data) }
                },
                doOnError = { error ->
                    showLoading(false)
                    binding.tvErrorWatchlist.isVisible = true
                    binding.tvErrorWatchlist.text = error.exception?.message

                },
                doOnLoading = {
                    binding.tvErrorWatchlist.isVisible = false
                    showLoading(true)
                },
                doOnEmpty = {
                    showLoading(false)
                    binding.tvErrorWatchlist.isVisible = true
                    binding.tvErrorWatchlist.text = getString(R.string.text_empty_watchlist)
                }
            )
        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.rvWatchlist.isVisible = !isShowLoading
        binding.pbWatchlist.isVisible = isShowLoading
    }

    private fun initRefresh() {
        binding.srlWatchlist.setOnRefreshListener {
            viewModel.fetchWatchlist()
            binding.srlWatchlist.isRefreshing = false
        }
    }

}