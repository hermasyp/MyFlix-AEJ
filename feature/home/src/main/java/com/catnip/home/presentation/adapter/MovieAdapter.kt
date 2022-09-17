package com.catnip.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.styling.databinding.ItemMoviePosterBinding
import com.catnip.styling.databinding.ItemMoviePosterGridBinding

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MovieAdapter(
    private val isGridLayout: Boolean = false,
    private val itemClicked: (MovieViewParam) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<MovieViewParam>()

    fun setItems(items : List<MovieViewParam>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (!isGridLayout) {
            PosterViewHolderImpl(
                ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                itemClicked
            )
        } else {
            GridPosterViewHolderImpl(
                ItemMoviePosterGridBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                itemClicked
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PosterViewHolder).bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

}

interface PosterViewHolder {
    fun bindView(item: MovieViewParam)
}

class PosterViewHolderImpl(
    private val binding: ItemMoviePosterBinding,
    private val itemClicked: (MovieViewParam) -> Unit
) : RecyclerView.ViewHolder(binding.root), PosterViewHolder {
    override fun bindView(item: MovieViewParam) {
        with(item) {
            binding.ivPoster.load(item.posterUrl)
            itemView.setOnClickListener { itemClicked.invoke(this) }
        }
    }
}

class GridPosterViewHolderImpl(
    private val binding: ItemMoviePosterGridBinding,
    private val itemClicked: (MovieViewParam) -> Unit
) : RecyclerView.ViewHolder(binding.root), PosterViewHolder {
    override fun bindView(item: MovieViewParam) {
        with(item) {
            binding.ivPoster.load(item.posterUrl)
            itemView.setOnClickListener { itemClicked.invoke(this) }
        }
    }
}
