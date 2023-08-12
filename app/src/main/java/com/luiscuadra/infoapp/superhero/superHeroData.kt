package com.luiscuadra.infoapp.superhero

import com.google.gson.annotations.SerializedName

data class superHeroData(
   @SerializedName("response") val isWorking:String
   ,@SerializedName("results") val superheores:List<superHeroItem>)

data class superHeroItem(
   @SerializedName("id") val superheroId:String,
   @SerializedName("name") val superheroName:String,
   @SerializedName("image") val superHeroImage: superHeroImage
)

data class superHeroImage(
   @SerializedName("url") val url:String
)

