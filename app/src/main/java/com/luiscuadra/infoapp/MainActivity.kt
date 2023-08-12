package com.luiscuadra.infoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.PreferencesProto.Value
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.luiscuadra.infoApp.databinding.ActivityMainBinding
import com.luiscuadra.infoapp.movies.infomovieActivity
import com.luiscuadra.infoapp.movies.infotvshowActivity
import com.luiscuadra.infoapp.superhero.infoSuperHeroActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    companion object{
        const val KEY_DARK_MODE = "key_dark_mode"
    }

    private lateinit var binding: ActivityMainBinding
    private var firstime:Boolean= true

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSuperHero.setOnClickListener {
            navigateToSuperHero()
        }

        binding.btnMovie.setOnClickListener {
            navigateToMovies()

        }

        binding.btnSerie.setOnClickListener {
            navigateToSeries()

        }

        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstime }.collect{ settingsMode ->
                if (settingsMode !=null)
                {
                    runOnUiThread {
                        binding.swDarkMode.isChecked= settingsMode.darkMode
                        firstime=!firstime
                    }

                }

            }
        }
        initUI()

    }

    private fun initUI() {
        binding.swDarkMode.setOnCheckedChangeListener { _, value ->
            if(value)
            {
                enableDarkMode()
            }
            else{
                disableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, value)
            }

        }
    }

        private suspend fun saveOptions(key:String, value:Boolean)
        {
            dataStore.edit { preferences ->
                preferences[booleanPreferencesKey(key)]=value
            }
        }

        private fun navigateToSuperHero() {
            val intent = Intent(this, infoSuperHeroActivity::class.java)
            startActivity(intent)
        }

        private fun navigateToMovies() {
            val intent = Intent(this, infomovieActivity::class.java)
            startActivity(intent)
        }

    private fun navigateToSeries() {
        val intent = Intent(this, infotvshowActivity::class.java)
        startActivity(intent)
    }

    private fun getSettings(): Flow<SettingsMode?> {
       return dataStore.data.map { preferences ->
           SettingsMode(
               darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)]?:false
           )

        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

    }