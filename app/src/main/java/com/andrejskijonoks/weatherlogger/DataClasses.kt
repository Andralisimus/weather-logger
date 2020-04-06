package com.andrejskijonoks.weatherlogger

import com.google.gson.annotations.SerializedName

data class City(
    var name: String,
    var isSelected : Boolean,
    var id : String
)

data class WeatherResponse (
    @SerializedName("cnt") val size : Int,
    @SerializedName("list") val weatherList : List<WeatherHolder>
)

data class WeatherHolder (
    @SerializedName("main") val main : Main,
    @SerializedName("name") val name : String,
    @SerializedName("id") val id : Int,
    @SerializedName("weather") val weather : List<Weather>
)

data class Main (
    @SerializedName("temp") val temp : Double,
    @SerializedName("temp_min") val temp_min : Double,
    @SerializedName("temp_max") val temp_max : Double,
    @SerializedName("feels_like") val feels_like : Double
)

data class Weather (
    @SerializedName("description") val description : String,
    @SerializedName("icon") val icon : String
)