package com.luiscuadra.infoapp.superhero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.luiscuadra.infoApp.databinding.ActivityInfoSuperHeroBinding
import com.luiscuadra.infoapp.superhero.DetailSuperHeroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class infoSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoSuperHeroBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: superHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
        searchByName("a")
    }

    private fun initUI() {
        binding.sView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                searchByName(query.orEmpty())

                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = superHeroAdapter{navigateDetail(it)}
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter
    }

    private fun searchByName(query:String){
        binding.pbLoading.isVisible=true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(apiService::class.java).getSuperHero(query)
            if(myResponse.isSuccessful){
                Log.i("Luis","Funciona :)")
               val response: superHeroData? = myResponse.body()
                if(response != null){
                    runOnUiThread {
                        binding.pbLoading.isVisible=false
                        adapter.udpateList(response.superheores)
                    }


                }

            }
            else{
                Log.i("Luis","No Funciona :(")
            }
        }
    }

    private fun getRetrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateDetail(id:String){
        val intent = Intent(this,DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}