package com.example.pogoda_mim.ui

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pogoda_mim.R
import com.example.pogoda_mim.data.remote.ForecastList
import com.example.pogoda_mim.data.remote.HourlyForecastResponse
import com.example.pogoda_mim.databinding.ItemHourlyForecastListBinding
import com.google.android.material.internal.ContextUtils.getActivity
import java.security.AccessController.getContext

class HourlyForecastAdapter(private val list: MutableList<ForecastList>): RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder>() {

        companion object{
            val TAG: String = "HourlyForecastAdapter"
        }
        lateinit var context: Context


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder {
            val binding = DataBindingUtil.inflate<ItemHourlyForecastListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_hourly_forecast_list, parent, false)
                context = parent.context
            return HourlyForecastViewHolder(binding)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
            val forcasts: ForecastList = list[position]
            holder.bind(forcasts, object : ForecastListener{
                override fun onForcastSelected(frecast: ForecastList) {
                    Toast.makeText(context,"Items are not clickable", Toast.LENGTH_SHORT).show()
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
                    mHourTv?.text = forecast.dtTxt.subSequence(11,16)
                    mTemperatureTv?.text = (forecast.main!!.temp - 273.15F).toInt().toString() + "°"
                    mHourImg?.setImageResource(
                        when
                        {
                            forecast.weather[0].icon == "01d" -> if(((forecast.dtTxt.subSequence(11,13)).toString().toInt()>7)
                                && (forecast.dtTxt.subSequence(11,13)).toString().toInt()< 18){
                            R.drawable.ic_wb_sunny_black_24dp
                        }else R.drawable.icons8_moon_and_stars_96

                            forecast.weather[0].icon == "02d" ->  if(((forecast.dtTxt.subSequence(11,13)).toString().toInt()>7)
                                && (forecast.dtTxt.subSequence(11,13)).toString().toInt()< 18){
                                R.drawable.ic_wb_sunny_black_24dp
                            }else R.drawable.icons8_moon_and_stars_96
                            forecast.weather[0].icon == "03d" -> R.drawable.ic_cloud_black_24dp
                            forecast.weather[0].icon == "04d" -> R.drawable.ic_cloud_black_24dp
                            forecast.weather[0].icon == "09d" -> R.drawable.icons8_rain_96
                            forecast.weather[0].icon == "10d" -> R.drawable.icons8_rain_96
                            forecast.weather[0].icon == "11d" -> R.drawable.icons8_storm_96
                            forecast.weather[0].icon == "13d" -> R.drawable.icons8_snow_96
                            forecast.weather[0].icon == "50d" -> R.drawable.icons8_dust_96
                            else ->  if(((forecast.dtTxt.subSequence(11,13)).toString().toInt()>7)
                                && (forecast.dtTxt.subSequence(11,13)).toString().toInt()< 18){
                                R.drawable.ic_wb_sunny_black_24dp
                            }else R.drawable.icons8_moon_and_stars_96
                        } as Int
                    )
                    executePendingBindings()
                }
            }
        }
}
interface ForecastListener {
    fun onForcastSelected(frecast: ForecastList)
}