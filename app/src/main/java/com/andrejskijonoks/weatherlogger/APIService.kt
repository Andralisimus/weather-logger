package com.andrejskijonoks.weatherlogger

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface APIService {

    @GET("data/2.5/group?")
    fun getWeatherForSeveralCitiesById(@Query("id") id : String,
                                       @Query("units") units : String,
                                       @Query("APPID") app_id : String) : retrofit2.Call<WeatherResponse>

    companion object {
        fun getInstance(): APIService {
            val retrofit = Retrofit.Builder()

                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}
