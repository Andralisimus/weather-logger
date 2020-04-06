package com.andrejskijonoks.weatherlogger.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrejskijonoks.weatherlogger.APIService
import com.andrejskijonoks.weatherlogger.Adapters.CitiesAdapter
import com.andrejskijonoks.weatherlogger.City
import com.andrejskijonoks.weatherlogger.Database.SavedWeather
import com.andrejskijonoks.weatherlogger.R
import com.andrejskijonoks.weatherlogger.WeatherResponse
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_load.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.text.SimpleDateFormat


class LoadFragment : Fragment() {

    lateinit var weatherList : List<City>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.andrejskijonoks.weatherlogger.R.layout.fragment_load, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fillList()
        LoadFloatingButton.hide()
        LoadFloatingButton.setOnClickListener {
            call()
            LoadFloatingButton.isEnabled = false
        }
    }

    private fun call(){
        val apiService = APIService.getInstance()
        val call = apiService.getWeatherForSeveralCitiesById(
            makeStringOfCities(),getString(com.andrejskijonoks.weatherlogger.R.string.metric),getString(
                com.andrejskijonoks.weatherlogger.R.string.app_id
            )
        )

        val dateFormat = SimpleDateFormat("EEE, MMM d hh:mm")
        val currentTime = Calendar.getInstance().getTime()
        val requestTime = dateFormat.format(currentTime)

        call.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {


                var weatherList = response.body()!!.weatherList

                if(response.isSuccessful){

                    weatherList.forEach {
                        var savedWeather = SavedWeather(
                            it.id,
                            it.name,
                            lessDetails(it.main.temp.toString()),
                            lessDetails(it.main.temp_min.toString()),
                            lessDetails(it.main.temp_max.toString()),
                            lessDetails(it.main.feels_like.toString()),
                            it.weather[0].description,
                            it.weather[0].icon,
                            requestTime
                        )
                        MainActivity.db!!.savedWeatherDao().insertWeather(savedWeather)
                    }
                    MainActivity.recreateSavedFragment(activity!!.supportFragmentManager)
                    showSnackBar(true)
                    LoadFloatingButton.isEnabled = true
                }
                else {
                    showSnackBar(false)
                    LoadFloatingButton.isEnabled = true
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                showSnackBar(false)
                LoadFloatingButton.isEnabled = true
            }
        })
    }

    private fun showSnackBar(success : Boolean){
        var message = if(success) getString(R.string.success_message)
        else getString(R.string.error_message)
        var snackbar = Snackbar.make(view!!,message, Snackbar.LENGTH_LONG)
        snackbar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
        snackbar.show()
    }

    private fun lessDetails(temp : String) : String{
        var index = temp.indexOf('.')
        return if(index != -1) temp.substring(0,index) + '°'
        else "$temp°"
    }

    private fun makeStringOfCities() : String{
        var cities = ""
        weatherList.forEach {
            if(it.isSelected) cities += it.id + ","
        }
        return cities.dropLast(1)
    }

    private fun fillList(){
        weatherList = listOf(
            City("Riga", false, "456172"),
            City("Daugavpils", false, "460413"),
            City("London", false, "2643743"),
            City("Berlin",false,"2950158"),
            City("East Melbourne",false,"6952201"),
            City("Tenerife",false,"2511173"),
            City("Seattle",false,"5809844")
        )
        LoadListView.adapter = CitiesAdapter(activity,weatherList,LoadFloatingButton)
    }
}
