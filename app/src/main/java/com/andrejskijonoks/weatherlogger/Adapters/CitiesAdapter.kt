package com.andrejskijonoks.weatherlogger.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andrejskijonoks.weatherlogger.City
import com.andrejskijonoks.weatherlogger.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.cities_row.view.*

class CitiesAdapter(context: Context?, private val cities : List<City>, private val floatingButton : FloatingActionButton) : BaseAdapter() {

    private val layoutInflater: LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cities_row,null, false)
            convertView!!.tag = CitiesViewHolder(convertView)
        }
        val holder = convertView.tag as CitiesViewHolder

        holder.tvCity.text = cities[position].name
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            cities[position].isSelected = isChecked
            checkFBVisibility()
        }

        return convertView
    }

    override fun getItem(p0: Int): Any {
        return cities[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return cities.size
    }

    private fun checkFBVisibility(){
        var show = false
        cities.forEach {
            if(it.isSelected){
                show = true
            }
        }
        if(show) floatingButton.show()
        else floatingButton.hide()
    }

    private inner class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCity: TextView by lazy{itemView.citiesRowCity}
        val checkBox: CheckBox by lazy {itemView.citiesRowCB}
    }
}