package com.andrejskijonoks.weatherlogger.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedWeather(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "city_name") val cityName: String?,
    @ColumnInfo(name = "temperature") val temperature: String?,
    @ColumnInfo(name = "min_temp") val minTemp: String?,
    @ColumnInfo(name = "max_temp") val maxTemp: String?,
    @ColumnInfo(name = "feels_like") val feelsLike: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "icon") val icon: String?,
    @ColumnInfo(name = "time") val time: String?
)