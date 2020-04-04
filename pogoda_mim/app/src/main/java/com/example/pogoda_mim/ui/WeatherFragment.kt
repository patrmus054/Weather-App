package com.example.pogoda_mim.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pogoda_mim.R
import com.example.pogoda_mim.data.remote.DailyForecastList
import com.example.pogoda_mim.data.remote.DailyForecastResponse
import com.example.pogoda_mim.data.remote.ForecastList
import com.example.pogoda_mim.data.remote.HourlyForecastResponse
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.tv_temperature
import kotlinx.android.synthetic.main.item_hourly_forecast_list.*
import java.lang.Exception
import java.util.*
import kotlin.random.Random

private lateinit var mhr: MutableList<ForecastList>
private lateinit var mdr: MutableList<DailyForecastList>
lateinit var weatherViewModel: WeatherViewModel
lateinit var HourlyAdapter: HourlyForecastAdapter
lateinit var DailyAdapter: DailyForecastedAdapter
class WeatherFragment: Fragment(){

    companion object{
        fun newInstance() = WeatherFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mhr = mutableListOf()
        HourlyAdapter = HourlyForecastAdapter(mhr)
        mdr = mutableListOf()
        DailyAdapter = DailyForecastedAdapter(mdr)

        rv_hour.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = HourlyAdapter
        }
        rv_day.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DailyAdapter
        }
        weatherViewModel.item.observe(this, Observer {
            var calendar = Calendar.getInstance()
            tv_location.text = it.name
            //tv_temperature.text = ((it.main!!.temp - 32) * (5/9)).toString()
            tv_temperature.text = (it.main!!.temp - 273.15F).toInt().toString() + "°"
            img.setImageResource(
                when{
                    it.waether[0].icon == "01d" -> if(((calendar.time.toString().subSequence(11,13)).toString().toInt()>7)
                        && (calendar.time.toString().subSequence(11,13)).toString().toInt()< 18){
                        R.drawable.ic_wb_sunny_black_24dp
                    }else R.drawable.icons8_moon_and_stars_96
                    it.waether[0].icon == "02d" -> if(((calendar.time.toString().subSequence(11,13)).toString().toInt()>7)
                        && (calendar.time.toString().subSequence(11,13)).toString().toInt()< 18){
                        R.drawable.ic_wb_sunny_black_24dp
                    }else R.drawable.icons8_moon_and_stars_96
                    it.waether[0].icon == "03d" -> R.drawable.ic_cloud_black_24dp
                    it.waether[0].icon == "04d" -> R.drawable.ic_cloud_black_24dp
                    it.waether[0].icon == "09d" -> R.drawable.icons8_rain_96
                    it.waether[0].icon == "10d" -> R.drawable.icons8_rain_96
                    it.waether[0].icon == "11d" -> R.drawable.icons8_storm_96
                    it.waether[0].icon == "13d" -> R.drawable.icons8_snow_96
                    it.waether[0].icon == "50d" -> R.drawable.icons8_dust_96
                    else ->  R.drawable.ic_wb_sunny_black_24dp
                }
            )
            tv_humidity.text = "Humidity" + "\n" + it.main!!.humidity + "%"
            tv_wind.text = "Wind\n" +
                    "speed: " + it.wind!!.speed + " m/s\n" + "" +
                    "deg: " + it.wind!!.deg + "°"
            tv_air_pressure.text = "Pressure\n" + it.main!!.pressure + " hpa"
            tv_uv.text = "UV\n " + Random.nextInt(0, 10) // nie ma tego w api a bardzo chcialem to dodac :)
        })
        weatherViewModel.hoursItems.observe(this, Observer {
            HourlyAdapter.setList(it)
        })
        weatherViewModel.dailyItems.observe(this, Observer {
            DailyAdapter.setList(it)
        })

            weatherViewModel.getWeather()
            weatherViewModel.getHourlyForcast()
            weatherViewModel.getDailyForecast()




    }
//todo back button navigate to searvh fragment
//todo wrong city error  handling
}
