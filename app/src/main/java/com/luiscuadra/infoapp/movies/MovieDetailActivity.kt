package com.luiscuadra.infoapp.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luiscuadra.infoApp.databinding.ActivityMovieDetailBinding
import com.luiscuadra.infoapp.movies.TmdbMovie
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            val movieId = bundle.getInt("MOVIE_ID")
            val moviePosterUrl = intent.getStringExtra("MOVIE_POSTER_URL")
            val movieTitle = bundle.getString("MOVIE_TITLE")
            val movieOverview = bundle.getString("MOVIE_OVERVIEW")
            val moviePosterPath = bundle.getString("MOVIE_POSTER_PATH")
            val movieVoteAverage = bundle.getDouble("MOVIE_VOTE_AVERAGE")
            val moviePopularity = bundle.getDouble("MOVIE_POPULARITY")
            val movieReleaseDate = bundle.getString("MOVIE_RELEASE_DATE")
            val movieVoteCount = bundle.getInt("MOVIE_VOTE_COUNT")


            supportActionBar?.title = movieTitle
            binding.MovieName.text=movieTitle
            binding.overview.text = movieOverview
            val decimalFormatRating = DecimalFormat("#.#")
            val formattedRating = decimalFormatRating.format(moviePopularity)
            binding.rating.text = "Popularidad: $formattedRating"
            binding.date.text = "Fecha de lanzamiento: $movieReleaseDate"
            val decimalFormat = DecimalFormat("#.#")
            val formattedVoteAverage = decimalFormat.format(movieVoteAverage)
            binding.voteAverage.text = "Voto Promedio: $formattedVoteAverage"
            binding.voteCount.text = "Numero de Votos: $movieVoteCount"
            Picasso.get().load(moviePosterUrl).into(binding.imgDetailMovie)


        }
    }
}