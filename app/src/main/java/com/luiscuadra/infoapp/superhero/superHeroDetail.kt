package com.luiscuadra.infoapp.superhero

import com.google.gson.annotations.SerializedName

data class superHeroDetail(
    @SerializedName("name") val name:String,
    @SerializedName("powerstats") val powerStats: powerStats,
    @SerializedName("image") val image: superHeroImageDetail,
    @SerializedName("biography") val biography: biography,
    @SerializedName("appearance") val appearance:appearance


)

data class powerStats(
    @SerializedName("intelligence") val intelligence:String,
    @SerializedName("strength") val strength:String,
    @SerializedName("speed") val speed:String,
    @SerializedName("durability") val durability:String,
    @SerializedName("power") val power:String,
    @SerializedName("combat") val combat:String

)

data class biography(
    @SerializedName("full-name") val fullName:String,
    @SerializedName("first-appearance") val appearance:String,
    @SerializedName("publisher") val publisher:String,
)

data class appearance(
    @SerializedName("gender") val gender:String,
    @SerializedName("race") val race:String
)

data class superHeroImageDetail(
    @SerializedName("url") val url:String
)
