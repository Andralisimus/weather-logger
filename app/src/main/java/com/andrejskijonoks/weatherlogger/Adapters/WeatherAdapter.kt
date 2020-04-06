package com.andrejskijonoks.weatherlogger.Adapters


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager

import androidx.recyclerview.widget.RecyclerView
import com.andrejskijonoks.weatherlogger.Database.SavedWeather
import kotlinx.android.synthetic.main.weather_row.view.*
import com.andrejskijonoks.weatherlogger.R
import com.andrejskijonoks.weatherlogger.UI.DetailsActivity
import com.andrejskijonoks.weatherlogger.UI.MainActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception


class WeatherAdapter(context: Context?, private val weathers : List<SavedWeather>,fm : FragmentManager) : BaseAdapter() {

    private val context = context
    private val fragmentManager = fm
    private val layoutInflater: LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.weather_row,null, false)
            convertView!!.tag = CitiesViewHolder(convertView)
        }
        val holder = convertView.tag as CitiesViewHolder
        val weather = weathers[position]

        holder.tvCity.text = weather.cityName
        holder.tvTemp.text = weather.temperature + 'C'
        holder.btnOptions.setOnClickListener { showPopup(it,position) }
        holder.tvTime.text = weather.time
        holder.img.setImageResource(MainActivity.setWeatherImage(weather.icon!!))

        return convertView
    }

    override fun getItem(p0: Int): Any {
        return weathers[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return weathers.size
    }

    private fun showPopup(view: View,position: Int){
        val popup = PopupMenu(context,view)
        popup.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.details -> {
                    context!!.startActivity(DetailsActivity.newIntent(context,weathers[position]))
                    true
                }
                R.id.delete -> {
                    MainActivity.db!!.savedWeatherDao().delete(weathers[position])
                    var snackbar = Snackbar.make(view,context!!.getString(R.string.delete_success), Snackbar.LENGTH_SHORT)
                    snackbar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                    snackbar.show()
                    MainActivity.recreateSavedFragment(fragmentManager)

                    true
                }
                else -> false
            }
        }

        popup.inflate(R.menu.popup_menu)

        try {
            val fieldWPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldWPopup.isAccessible = true
            val mPopup = fieldWPopup.get(popup)
            mPopup.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java).invoke(mPopup,true)
        } catch (e: Exception){
            Log.e("WeatherAdapter","Error showing menu icons.", e)
        } finally {
            popup.show()
        }
    }

    private inner class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCity: TextView by lazy{itemView.weatherRowCity}
        val tvTemp: TextView by lazy{itemView.weatherRowTemp}
        val btnOptions: Button by lazy{itemView.weatherRowOptions}
        val tvTime: TextView by lazy {itemView.weatherRowTime}
        val img: ImageView by lazy {itemView.weatherRowImg}

    }
}