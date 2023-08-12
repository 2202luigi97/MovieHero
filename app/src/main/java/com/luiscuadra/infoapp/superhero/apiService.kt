package com.luiscuadra.infoapp.superhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface apiService {

    @GET("/api/1518782122257987/search/{name}")
    suspend fun getSuperHero(@Path("name") superHeroName:String):Response<superHeroData>

    @GET("/api/1518782122257987/{id}")
    suspend fun getSuperHeroId(@Path("id") superHeroId:String):Response<superHeroDetail>



}