package com.luiscuadra.infoapp.movies

import androidx.recyclerview.widget.RecyclerView
import com.luiscuadra.infoApp.databinding.ItemMoviesBinding
import com.squareup.picasso.Picasso

class MovieViewHolder (private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: TmdbMovie) {
        binding.tvMovieName.text = movie.title
        val imageUrl = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
        Picasso.get().load(imageUrl).into(binding.imgMovie)
    }
}