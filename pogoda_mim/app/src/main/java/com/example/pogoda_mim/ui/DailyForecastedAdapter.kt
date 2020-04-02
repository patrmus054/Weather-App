package com.example.pogoda_mim.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pogoda_mim.R
import com.example.pogoda_mim.data.remote.DailyForecastList
import com.example.pogoda_mim.data.remote.ForecastList
import com.example.pogoda_mim.databinding.ItemDailyForecastListBinding

class DailyForecastedAdapter(private val list: MutableList<DailyForecastList>): RecyclerView.Adapter<DailyForecastedAdapter.DailyForecastViewHolder>() {

        companion object{
            val TAG: String = "DailyForecastAdapter"
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
            val binding = DataBindingUtil.inflate<ItemDailyForecastListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_daily_forecast_list, parent, false)

            return DailyForecastViewHolder(binding)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
            val forcasts: DailyForecastList = list[position]
            holder.bind(forcasts, object : DailyForecastListener{
                override fun onDailyForcastSelected(frecast: ForecastList) {
                    TODO("Not yet implemented")
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
            private var mHourImg: ImageView? = null

            init {
                mDailyTv = itemView.findViewById(R.id.tv_day)
                mTempMinTv = itemView.findViewById(R.id.tv_day_min_temperature)
                mTempMaxTv = itemView.findViewById(R.id.tv_day_min_temperature)
                mHourImg = itemView.findViewById(R.id.img_day_image)
            }

            fun bind(forecast: DailyForecastList, forecastListener: DailyForecastListener) {
                with(binding)
                {
                    forecastedDays = forecast
                    dailyListener = forecastListener
                    mDailyTv?.text = forecast.dt.toString()
                    mTempMinTv?.text = forecast.temp.day.toString()
                    mTempMaxTv?.text = forecast.temp.day.toString()
                    mHourImg?.setImageDrawable(R.drawable.icons8_snow_96.toDrawable())
                    executePendingBindings()
                }
            }
        }
    }

    interface DailyForecastListener {
        fun onDailyForcastSelected(frecast: ForecastList) }