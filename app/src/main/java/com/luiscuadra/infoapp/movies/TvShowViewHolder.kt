package com.luiscuadra.infoapp.movies

import androidx.recyclerview.widget.RecyclerView
import com.luiscuadra.infoApp.databinding.ItemTvshowsBinding
import com.squareup.picasso.Picasso

class TvShowViewHolder (private val binding: ItemTvshowsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tvShow: TmdbTvShow) {
        binding.tvTvShowName.text = tvShow.name
        val imageUrl = "https://image.tmdb.org/t/p/w500/${tvShow.posterPath}"
        Picasso.get().load(imageUrl).into(binding.imgTvShow)
    }
}