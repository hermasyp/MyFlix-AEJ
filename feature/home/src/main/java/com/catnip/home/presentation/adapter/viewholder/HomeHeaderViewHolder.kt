package com.catnip.home.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.catnip.home.databinding.ItemHeaderHomeBinding
import com.catnip.home.presentation.adapter.HomeAdapterClickListener
import com.catnip.home.presentation.viewparam.homeitem.HomeUiItem
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.shared.utils.CommonUtils

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class HomeHeaderViewHolder(
    private val binding: ItemHeaderHomeBinding,
    private val listener: HomeAdapterClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(item: HomeUiItem.HeaderSectionItem) {
        with(item.movieViewParam) {
            binding.tvAddToWatchlistHeader.setCompoundDrawablesWithIntrinsicBounds(
                0,
                CommonUtils.getWatchlistIcon(isUserWatchlist), 0, 0
            )
            binding.ivHeaderMovie.load(this.posterUrl)
            binding.tvTitleMovie.text = this.title
            binding.tvInfoHeader.setOnClickListener {
                listener.onMovieClicked(this)
            }
            binding.tvAddToWatchlistHeader.setOnClickListener {
                listener.onMyListClicked(this)
            }
            binding.cvPlayHeader.setOnClickListener {
                listener.onPlayMovieClicked(this)
            }
        }
    }

}

