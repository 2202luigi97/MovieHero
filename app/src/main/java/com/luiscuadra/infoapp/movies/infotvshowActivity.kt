package com.luiscuadra.infoapp.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.luiscuadra.infoApp.R
import com.luiscuadra.infoApp.databinding.ActivityInfotvshowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class infotvshowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfotvshowBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfotvshowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        initUI()
        searchTvShows("a")
    }

    private fun initUI() {
        binding.sView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    searchTvShows(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Implement if needed
                return false
            }
        })

        adapter = TvShowAdapter()
        binding.rvTvShow.layoutManager = LinearLayoutManager(this)
        binding.rvTvShow.adapter = adapter

        adapter.setItemClickListener { tvShow ->
            val intent = Intent(this, TvshowDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("TV_SHOW_ID", tvShow.id)
            bundle.putString("TV_SHOW_TITLE", tvShow.name)
            bundle.putDouble("MOVIE_VOTE_AVERAGE", tvShow.voteAverage)
            bundle.putString("TV_SHOW_OVERVIEW", tvShow.overview)
            bundle.putString("TV_SHOW_POSTER_PATH", tvShow.posterPath)
            bundle.putDouble("TV_SHOW_POPULARITY", tvShow.popularity)
            bundle.putString("TV_SHOW_FIRST_AIR_DATE", tvShow.firstAirDate)
            bundle.putInt("MOVIE_VOTE_COUNT", tvShow.voteCount)
            bundle.putString("TV_SHOW_POSTER_URL", "https://image.tmdb.org/t/p/w500/${tvShow.posterPath}")
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun searchTvShows(query: String) {
        binding.pbLoading.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val apiKey = "a99f7779e2251883ded429bcb4cc3510" // Reemplaza esto con tu API key
            val response = retrofit.create(TmdbApiService::class.java).searchTvShows(apiKey, query)
            if (response.isSuccessful) {
                val tmdbTvShowSearchResponse = response.body()
                val tvShows = tmdbTvShowSearchResponse?.results
                if (tvShows != null) {
                    runOnUiThread {
                        binding.pbLoading.isVisible = false
                        adapter.updateList(tvShows)
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