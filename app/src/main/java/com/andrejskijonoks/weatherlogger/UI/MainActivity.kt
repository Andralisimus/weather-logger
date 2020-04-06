package com.andrejskijonoks.weatherlogger.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.room.Room
import com.andrejskijonoks.weatherlogger.Database.AppDatabase
import com.andrejskijonoks.weatherlogger.R


class MainActivity : AppCompatActivity() {

    companion object StaticContent {
        var db: AppDatabase? = null
        var savedFragment = SavedFragment()

        fun recreateSavedFragment(fragmentManager: FragmentManager){
            val ft = fragmentManager.beginTransaction()
            ft.detach(savedFragment).attach(savedFragment).commit()
        }

        fun setWeatherImage(imageId : String) : Int {
            return if(imageId == "01d") R.drawable.ic_01d
            else if(imageId == "01n") R.drawable.ic_01n
            else if(imageId == "02n") R.drawable.ic_02n
            else if(imageId == "02d") R.drawable.ic_02d
            else if(imageId == "03d" || imageId == "03n") R.drawable.ic_03dn
            else if(imageId == "04d" || imageId == "04n") R.drawable.ic_04dn
            else if(imageId == "09d" || imageId == "09n") R.drawable.ic_09dn
            else if(imageId == "10d") R.drawable.ic_10d
            else if(imageId == "10n") R.drawable.ic_10n
            else if(imageId == "11d" || imageId == "11n") R.drawable.ic_11dn
            else if(imageId == "13d" || imageId == "13n") R.drawable.ic_13dn
            else if(imageId == "50d" || imageId == "50n") R.drawable.ic_50dn
            else R.drawable.ic_03dn
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.removeAllTabs()
        tabLayout.addTab(tabLayout.newTab().setText(com.andrejskijonoks.weatherlogger.R.string.load).setIcon(
            R.drawable.ic_cloud_download_black_24dp
        ))
        tabLayout.addTab(tabLayout.newTab().setText(com.andrejskijonoks.weatherlogger.R.string.saved).setIcon(
            R.drawable.ic_storage_black_24dp
        ))

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "weather-logger-db").allowMainThreadQueries().build()

    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment {
            when(position){
                0 -> return LoadFragment()
                1 -> return savedFragment
            }
            return LoadFragment()
        }
    }
}
