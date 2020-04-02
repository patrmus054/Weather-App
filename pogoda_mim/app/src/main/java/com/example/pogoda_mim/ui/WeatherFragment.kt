package com.example.pogoda_mim.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pogoda_mim.R
import com.example.pogoda_mim.data.remote.DailyForecastList
import com.example.pogoda_mim.data.remote.DailyForecastResponse
import com.example.pogoda_mim.data.remote.ForecastList
import com.example.pogoda_mim.data.remote.HourlyForecastResponse
import kotlinx.android.synthetic.main.fragment_weather.*

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
            layoutManager = LinearLayoutManager(activity)
            adapter = HourlyAdapter
        }
        rv_day.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DailyAdapter
        }
        weatherViewModel.item.observe(this, Observer {
            //
            // tv_fragment.text = it.name
        })
        weatherViewModel.hoursItems.observe(this, Observer {
            HourlyAdapter.setList(it)
        })
        weatherViewModel.dailyItems.observe(this, Observer {
            DailyAdapter.setList(it)
        })
        weatherViewModel.getWeather("London","456de66f6dc8cc38e82dbae674a857dd")
        weatherViewModel.getHourlyForcast("London","456de66f6dc8cc38e82dbae674a857dd")
        weatherViewModel.getDailyForecast("London","456de66f6dc8cc38e82dbae674a857dd")

    }


}
