package com.example.pogoda_mim.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pogoda_mim.R
import com.example.pogoda_mim.data.remote.ForecastList
import com.example.pogoda_mim.data.remote.HourlyForecastResponse
import com.example.pogoda_mim.databinding.ItemHourlyForecastListBinding

class HourlyForecastAdapter(private val list: MutableList<ForecastList>): RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder>() {

        companion object{
            val TAG: String = "HourlyForecastAdapter"
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder {
            val binding = DataBindingUtil.inflate<ItemHourlyForecastListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_hourly_forecast_list, parent, false)

            return HourlyForecastViewHolder(binding)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
            val forcasts: ForecastList = list[position]
            holder.bind(forcasts, object : ForecastListener{
                override fun onForcastSelected(frecast: ForecastList) {
                    TODO("Not yet implemented")
                }
            })
        }


        fun setList(newList: List<ForecastList>){
            list.clear()
            list.addAll(newList)
            notifyDataSetChanged()
        }


        class HourlyForecastViewHolder(private val binding: ItemHourlyForecastListBinding) : RecyclerView.ViewHolder(binding.root) {
            private var mHourTv: TextView? = null
            private var mTemperatureTv: TextView? = null
            private var mHourImg: ImageView? = null

            init {
                mHourTv = itemView.findViewById(R.id.tv_hour)
                mTemperatureTv = itemView.findViewById(R.id.tv_temperature)
                mHourImg = itemView.findViewById(R.id.img_item)
            }

            fun bind(forecast: ForecastList, forecastListener: ForecastListener) {
                with(binding)
                {
                    forecastedHour = forecast
                    listener = forecastListener
                    mHourTv?.text = forecast.main.humidity.toString()
                    mTemperatureTv?.text = forecast.main.temp.toString()
                    mHourImg?.setImageDrawable(R.drawable.icons8_sun_50.toDrawable())
                    executePendingBindings()
                }
            }
        }
}
interface ForecastListener {
    fun onForcastSelected(frecast: ForecastList)
}