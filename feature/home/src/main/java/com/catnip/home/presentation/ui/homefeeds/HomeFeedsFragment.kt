package com.catnip.home.presentation.ui.homefeeds

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catnip.core.base.BaseFragment
import com.catnip.home.R
import com.catnip.home.databinding.FragmentHomeFeedsBinding
import com.catnip.home.presentation.adapter.HomeAdapter
import com.catnip.home.presentation.adapter.HomeAdapterClickListener
import com.catnip.home.presentation.ui.home.HomeViewModel
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.shared.router.ActivityRouter
import com.catnip.shared.router.BottomSheetRouter
import com.catnip.shared.utils.ColorUtils
import com.catnip.shared.utils.ext.subscribe
import com.catnip.shared.utils.textdrawable.ColorGenerator
import com.catnip.shared.utils.textdrawable.TextDrawable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.min

class HomeFeedsFragment : BaseFragment<FragmentHomeFeedsBinding, HomeViewModel>(
    FragmentHomeFeedsBinding::inflate
) {

    override val viewModel: HomeViewModel by sharedViewModel()

    private val bottomSheetRouter: BottomSheetRouter by inject()

    private val activityRouter: ActivityRouter by inject()

    private val recyclerViewPool: RecyclerView.RecycledViewPool by lazy {
        RecyclerView.RecycledViewPool()
    }

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(object : HomeAdapterClickListener {
            override fun onMyListClicked(movieViewParam: MovieViewParam) {
                viewModel.addOrRemoveWatchlist(movieViewParam)
            }

            override fun onPlayMovieClicked(movieViewParam: MovieViewParam) {
                startActivity(
                    activityRouter.playerActivity(
                        requireContext(),
                        movieViewParam.videoUrl
                    )
                )
            }

            override fun onMovieClicked(movieViewParam: MovieViewParam) {
                bottomSheetRouter.createMovieInfoBottomSheet(movieViewParam)
                    .show(childFragmentManager, null)
            }
        }, recyclerViewPool)
    }

    private fun setupRecyclerView() {
        binding.rvHome.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setRecycledViewPool(recycledViewPool)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val scrollY: Int = binding.rvHome.computeVerticalScrollOffset()
                    val color = ColorUtils.changeAlpha(
                        ContextCompat.getColor(requireActivity(), R.color.black_transparent),
                        (min(255, scrollY).toFloat() / 255.0f).toDouble()
                    )
                    binding.clToolbarHomeFeed.setBackgroundColor(color)
                }
            })
        }
    }


    override fun initView() {
        setupRecyclerView()
        initData()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.ivAvatarUser.setOnClickListener {
            startActivity(activityRouter.profileActivity(requireContext()))
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.homeFeedsResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    showLoading(false)
                    result.payload?.let { data ->
                        homeAdapter.setItems(data)
                    }
                },
                doOnLoading = {
                    showLoading(true)
                },
                doOnError = { error ->
                    showLoading(false)
                    error.exception?.let { e -> showError(true, e) }

                })
        }
        viewModel.currentUserResult.observe(this) {
            it.subscribe(doOnSuccess = { result ->
                binding.ivAvatarUser.setImageDrawable(
                    TextDrawable.builder()
                        .beginConfig()
                        .bold()
                        .toUpperCase()
                        .endConfig()
                        .buildRect(
                            result.payload?.username?.get(0).toString(),
                            ColorGenerator.MATERIAL.randomColor
                        )
                )
            })
        }
        viewModel.getWatchlistResult().observe(this) {
            it.subscribe(
                doOnSuccess = {
                    Toast.makeText(
                        requireContext(),
                        if (it.payload?.isUserWatchlist == true)
                            getString(R.string.text_add_watchlist_success)
                        else
                            getString(R.string.text_remove_watchlist_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }, doOnError = {

                })
        }
    }

    private fun initData() {
        viewModel.getCurrentUser()
        viewModel.fetchHome()
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbHome.isVisible = isShowLoading
    }

}