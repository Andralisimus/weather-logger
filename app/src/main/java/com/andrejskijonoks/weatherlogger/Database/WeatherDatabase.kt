package com.andrejskijonoks.weatherlogger.Database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(SavedWeather::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedWeatherDao(): SavedWeatherDao
}