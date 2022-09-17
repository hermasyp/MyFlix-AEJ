package com.catnip.home.presentation.adapter.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catnip.home.databinding.ItemSectionMovieBinding
import com.catnip.home.presentation.adapter.MovieAdapter
import com.catnip.home.presentation.viewparam.homeitem.HomeUiItem
import com.catnip.shared.data.model.viewparam.MovieViewParam

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class HomeSectionViewHolder(
    private val binding: ItemSectionMovieBinding,
    private val recyclerViewPool: RecyclerView.RecycledViewPool,
    private val onMovieClicked: (MovieViewParam) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.rvContents.apply {
            setRecycledViewPool(recyclerViewPool)
            adapter = movieAdapter
            layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter {
            onMovieClicked.invoke(it)
        }
    }

    fun bindView(item: HomeUiItem.MovieSectionItem) {
        with(item) {
            binding.tvTitleSection.text = this.sectionViewParam.sectionName
            movieAdapter.setItems(this.sectionViewParam.contents)
        }
    }


}
