package com.luiscuadra.infoapp.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Intent
import com.luiscuadra.infoApp.databinding.ActivityInfomovieBinding
import com.luiscuadra.infoapp.movies.TmdbMovie

class infomovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfomovieBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfomovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        initUI()
        searchMovies("a")
    }

    private fun initUI() {
        binding.sView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    searchMovies(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Implement if needed
                return false
            }
        })

        adapter = MovieAdapter()
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.adapter = adapter

        adapter.setItemClickListener { movie ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("MOVIE_ID", movie.id)
            bundle.putDouble("MOVIE_VOTE_AVERAGE", movie.voteAverage)
            bundle.putString("MOVIE_TITLE", movie.title)
            bundle.putString("MOVIE_OVERVIEW", movie.overview)
            bundle.putString("MOVIE_POSTER_PATH", movie.posterPath)
            bundle.putDouble("MOVIE_POPULARITY", movie.popularity)
            bundle.putString("MOVIE_RELEASE_DATE", movie.releaseDate)
            bundle.putInt("MOVIE_VOTE_COUNT", movie.voteCount)
            bundle.putString("MOVIE_POSTER_URL", "https://image.tmdb.org/t/p/w500/${movie.posterPath}")
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun searchMovies(query: String) {
        binding.pbLoading.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val apiKey = "a99f7779e2251883ded429bcb4cc3510"
            val response = retrofit.create(TmdbApiService::class.java).searchMovies(apiKey, query)
            if (response.isSuccessful) {
                val tmdbMovieSearchResponse = response.body()
                val movies = tmdbMovieSearchResponse?.results
                if (movies != null) {
                    runOnUiThread {
                        binding.pbLoading.isVisible = false
                        adapter.updateList(movies)
                    }
                }
            } else {
                runOnUiThread {
                    binding.pbLoading.isVisible = false
                }
            }
        }
    }
}