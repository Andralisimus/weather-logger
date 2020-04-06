package com.andrejskijonoks.weatherlogger.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrejskijonoks.weatherlogger.Adapters.WeatherAdapter
import com.andrejskijonoks.weatherlogger.R
import kotlinx.android.synthetic.main.fragment_saved.*


class SavedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var listOfWeathers = MainActivity.db!!.savedWeatherDao().getAll()
        SavedListView.adapter = WeatherAdapter(activity,listOfWeathers,activity!!.supportFragmentManager)
    }
}
