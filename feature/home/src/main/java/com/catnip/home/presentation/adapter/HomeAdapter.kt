package com.catnip.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catnip.home.R
import com.catnip.home.databinding.ItemHeaderHomeBinding
import com.catnip.home.databinding.ItemSectionMovieBinding
import com.catnip.home.presentation.adapter.viewholder.HomeHeaderViewHolder
import com.catnip.home.presentation.adapter.viewholder.HomeSectionViewHolder
import com.catnip.home.presentation.viewparam.homeitem.HomeUiItem
import com.catnip.shared.data.model.viewparam.MovieViewParam

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class HomeAdapter(
    private val listener: HomeAdapterClickListener,
    private val recyclerViewPool: RecyclerView.RecycledViewPool
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val items = mutableListOf<HomeUiItem>()

    fun setItems(items : List<HomeUiItem>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_header_home -> {
                val binding = ItemHeaderHomeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HomeHeaderViewHolder(binding, listener)
            }
            else -> {
                val binding = ItemSectionMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HomeSectionViewHolder(binding,recyclerViewPool, listener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeHeaderViewHolder -> {
                holder.bindView(items[position] as HomeUiItem.HeaderSectionItem)
            }
            is HomeSectionViewHolder -> {
                holder.bindView(items[position] as HomeUiItem.MovieSectionItem)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is HomeUiItem.HeaderSectionItem -> R.layout.item_header_home
            is HomeUiItem.MovieSectionItem -> R.layout.item_section_movie
        }
    }

}

interface HomeAdapterClickListener {
    fun onMyListClicked(movieViewParam: MovieViewParam)
    fun onPlayMovieClicked(movieViewParam: MovieViewParam)
    fun onMovieClicked(movieViewParam: MovieViewParam)
}