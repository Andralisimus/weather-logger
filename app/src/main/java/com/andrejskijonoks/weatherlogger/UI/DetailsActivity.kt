package com.andrejskijonoks.weatherlogger.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrejskijonoks.weatherlogger.Database.SavedWeather
import com.andrejskijonoks.weatherlogger.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object ActivityIntent {

        private lateinit var savedWeather : SavedWeather

        fun newIntent(context: Context, savedWeather: SavedWeather): Intent {
            this.savedWeather = savedWeather
            return Intent(context, DetailsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        tvDetailsTown.text = savedWeather.cityName
        tvDetailsTime.text = savedWeather.time
        tvDetailsTemp.text = savedWeather.temperature
        tvDetailsFeelsLike.text = savedWeather.maxTemp + "/" + savedWeather.minTemp + " Feels like " + savedWeather.feelsLike
        tvDetailsDescription.text = savedWeather.description

        ivDetailsWeather.setImageResource(MainActivity.setWeatherImage(savedWeather.icon!!))
    }

}