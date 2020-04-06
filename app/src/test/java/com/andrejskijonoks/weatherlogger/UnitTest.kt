package com.andrejskijonoks.weatherlogger

import android.os.Build
import androidx.room.Room
import com.andrejskijonoks.weatherlogger.Database.AppDatabase
import com.andrejskijonoks.weatherlogger.Database.SavedWeather
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class RoomDBTests{
    var db: AppDatabase? = null
    var testWeather : SavedWeather? = null

    @Before
    fun setUp(){
        db = Room.databaseBuilder(RuntimeEnvironment.application, AppDatabase::class.java, "weather-logger-db").allowMainThreadQueries().build()
        testWeather = SavedWeather(1,"Test","Test","Test","Test","Test","Test","Test","Test")
    }

    @Test
    @Throws(Exception::class)
    fun testDBInsert(){
        db!!.savedWeatherDao().insertWeather(testWeather!!)
        assertEquals("Test",db!!.savedWeatherDao().getAll()[0].cityName)
    }

    @Test
    @Throws(Exception::class)
    fun testDBDelete(){
        db!!.savedWeatherDao().insertWeather(testWeather!!)
        db!!.savedWeatherDao().delete(testWeather!!)
        assertEquals(0,db!!.savedWeatherDao().getAll().size)
    }

    @Test
    @Throws(Exception::class)
    fun testDBonConflict(){
        db!!.savedWeatherDao().insertWeather(testWeather!!)
        db!!.savedWeatherDao().insertWeather(testWeather!!)
        assertEquals(1,db!!.savedWeatherDao().getAll().size)
    }

    @After
    fun removeWeather(){
        if(db!!.savedWeatherDao().getAll().isNotEmpty()){
            db!!.savedWeatherDao().delete(testWeather!!)
        }
    }
}