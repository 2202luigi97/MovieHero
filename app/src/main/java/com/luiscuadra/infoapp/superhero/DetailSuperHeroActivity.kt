package com.luiscuadra.infoapp.superhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.LayoutInflaterCompat
import androidx.core.view.isVisible
import com.luiscuadra.infoApp.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSUperHeroInformation(id)
    }

    private fun getSUperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
          val superHeroDetail =   getRetrofit().create(apiService::class.java).getSuperHeroId(id)

            if(superHeroDetail.body()!= null){
                runOnUiThread { createUI(superHeroDetail.body()!!) }
                
            }
        }
    }

    private fun createUI(superhero: superHeroDetail) {

        Picasso.get().load(superhero.image.url).into(binding.imgDetailHero)
        binding.superHeroName.text=superhero.name
        prepareStats(superhero.powerStats)
        binding.superHeroRealName.text= superhero.biography.fullName
        binding.superHeroAppareance.text=superhero.biography.appearance
        binding.superHeropublisher.text=superhero.biography.publisher
        binding.superHeroGender.text=superhero.appearance.gender
        binding.superHeroRace.text=superhero.appearance.race

    }

    private fun prepareStats(powerStats: powerStats) {

        updateHeight(binding.viewCombat, powerStats.combat)
        updateHeight(binding.viewStrength, powerStats.strength)
        updateHeight(binding.viewIntelligence, powerStats.intelligence)
        updateHeight(binding.viewSpeed, powerStats.speed)
        updateHeight(binding.viewDurability, powerStats.durability)
        updateHeight(binding.viewPower, powerStats.power)
    }

    private fun updateHeight(view:View,stat:String)
    {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams=params
    }

    private fun pxToDp(px:Float):Int
    {
      return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}