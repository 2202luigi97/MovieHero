package com.luiscuadra.infoapp.movies

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TmdbMovieSearchResponse(
    @SerializedName("results") val results: List<TmdbMovie>
)

data class TmdbMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)
