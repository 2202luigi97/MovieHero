package com.luiscuadra.infoapp.movies

import com.google.gson.annotations.SerializedName

data class TmdbTvShowSearchResponse(
    @SerializedName("results") val results: List<TmdbTvShow>
)

data class TmdbTvShow(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
@SerializedName("vote_count") val voteCount: Int
)
