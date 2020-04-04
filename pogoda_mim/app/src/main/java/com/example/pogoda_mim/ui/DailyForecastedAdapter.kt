package com.example.pogoda_mim.ui

import android.content.Context
import android.os.Build
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
import com.example.pogoda_mim.data.remote.DailyForecastList
import com.example.pogoda_mim.databinding.ItemDailyForecastListBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class DailyForecastedAdapter(private val list: MutableList<DailyForecastList>): RecyclerView.Adapter<DailyForecastedAdapter.DailyForecastViewHolder>() {

        companion object{
            val TAG: String = "DailyForecastAdapter"
        }

        lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
            val binding = DataBindingUtil.inflate<ItemDailyForecastListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_daily_forecast_list, parent, false)
                context = parent.context
            return DailyForecastViewHolder(binding)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
            val forcasts: DailyForecastList = list[position]
            holder.bind(forcasts, object : DailyForecastListener{
                override fun onDailyForcastSelected(frecast: DailyForecastList) {
                    Toast.makeText(context,"Items are not clickable", Toast.LENGTH_SHORT).show()
                }
            })
        }


        fun setList(newList: List<DailyForecastList>){
            list.clear()
            list.addAll(newList)
            notifyDataSetChanged()
        }


        class DailyForecastViewHolder(private val binding: ItemDailyForecastListBinding) : RecyclerView.ViewHolder(binding.root) {
            private var mDailyTv: TextView? = null
            private var mTempMinTv: TextView? = null
            private var mTempMaxTv: TextView? = null
            private var mDayImg: ImageView? = null

            init {
                mDailyTv = itemView.findViewById(R.id.tv_day)
                mTempMinTv = itemView.findViewById(R.id.tv_day_min_temperature)
                mTempMaxTv = itemView.findViewById(R.id.tv_day_min_temperature)
                mDayImg = itemView.findViewById(R.id.img_day_image)
            }
            val date = Calendar.getInstance()
            var days: Int = 0
            fun bind(forecast: DailyForecastList, forecastListener: DailyForecastListener) {
                with(binding)
                {

                    forecastedDays = forecast
                    dailyListener = forecastListener
                    date.add(Calendar.DATE, -days)
                    mDailyTv?.text = forecast.day
                    days += 1
                    Log.w(TAG, days.toString())
                    mTempMinTv?.text = (forecast.temp.min - 273.15F).toInt().toString() + "°"
                    mTempMaxTv?.text = (forecast.temp.max - 273.15F).toInt().toString() + "°"
                    mDayImg?.setImageResource(
                        when
                        {
                            forecast.weather[0].icon == "01d" -> if(((forecast.day.subSequence(11,13)).toString().toInt()>7)
                                && (forecast.day.subSequence(11,13)).toString().toInt()< 18){
                                R.drawable.ic_wb_sunny_black_24dp
                            }else R.drawable.icons8_moon_and_stars_96

                            forecast.weather[0].icon == "02d" ->  if(((forecast.day.subSequence(11,13)).toString().toInt()>7)
                                && (forecast.day.subSequence(11,13)).toString().toInt()< 18){
                                R.drawable.ic_wb_sunny_black_24dp
                            }else R.drawable.icons8_moon_and_stars_96
                            forecast.weather[0].icon == "03d" -> R.drawable.ic_cloud_black_24dp
                            forecast.weather[0].icon == "04d" -> R.drawable.ic_cloud_black_24dp
                            forecast.weather[0].icon == "09d" -> R.drawable.icons8_rain_96
                            forecast.weather[0].icon == "10d" -> R.drawable.icons8_rain_96
                            forecast.weather[0].icon == "11d" -> R.drawable.icons8_storm_96
                            forecast.weather[0].icon == "13d" -> R.drawable.icons8_snow_96
                            forecast.weather[0].icon == "50d" -> R.drawable.icons8_dust_96
                            else ->  if(((forecast.day.subSequence(11,13)).toString().toInt()>7)
                                && (forecast.day.subSequence(11,13)).toString().toInt()< 18){
                                R.drawable.ic_wb_sunny_black_24dp
                            }else R.drawable.icons8_moon_and_stars_96
                        }
                    )
                    executePendingBindings()
                }
            }
            fun getDaysAgo(days: Int): Date {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_YEAR, days)

                return calendar.time
            }
        }

}

interface DailyForecastListener {
    fun onDailyForcastSelected(frecast: DailyForecastList)
}