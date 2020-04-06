package com.andrejskijonoks.weatherlogger.Database

import androidx.room.*

@Dao
interface SavedWeatherDao {

    @Query("SELECT * FROM SavedWeather")
    fun getAll(): List<SavedWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: SavedWeather)

    @Delete
    fun delete(weather: SavedWeather)
}