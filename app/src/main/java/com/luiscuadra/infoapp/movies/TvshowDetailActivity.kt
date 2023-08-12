package com.luiscuadra.infoapp.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luiscuadra.infoApp.R
import com.luiscuadra.infoApp.databinding.ActivityTvshowDetailBinding
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class TvshowDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvshowDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvshowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            val tvShowTitle = bundle.getString("TV_SHOW_TITLE")
            val tvShowOverview = bundle.getString("TV_SHOW_OVERVIEW")
            val tvShowPosterUrl = intent.getStringExtra("TV_SHOW_POSTER_URL")
            val tvShowPopularity = bundle.getDouble("TV_SHOW_POPULARITY")
            val tvShowFirstAirDate = bundle.getString("TV_SHOW_FIRST_AIR_DATE")
            val movieVoteCount = bundle.getInt("MOVIE_VOTE_COUNT")
            val movieVoteAverage = bundle.getDouble("MOVIE_VOTE_AVERAGE")

            supportActionBar?.title = tvShowTitle
            binding.serieName.text = tvShowTitle
            binding.overview.text = tvShowOverview
            val decimalFormatRating = DecimalFormat("#.#")
            val formattedRating = decimalFormatRating.format(tvShowPopularity)
            binding.rating.text = "Popularidad: $formattedRating"
            binding.date.text = "Fecha de Lanzamiento: $tvShowFirstAirDate"
            val decimalFormat = DecimalFormat("#.#")
            val formattedVoteAverage = decimalFormat.format(movieVoteAverage)
            binding.voteAverage.text = "Voto Promedio: $formattedVoteAverage"
            Picasso.get().load(tvShowPosterUrl).into(binding.imgDetailMovie)
            binding.voteCount.text="Numero de votos: $movieVoteCount"
        }
    }
}