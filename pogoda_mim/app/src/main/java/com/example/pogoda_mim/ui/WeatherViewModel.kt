package com.example.pogoda_mim.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pogoda_mim.Repository
import com.example.pogoda_mim.data.remote.DailyForecastList
import com.example.pogoda_mim.data.remote.ForecastList
import com.example.pogoda_mim.data.remote.RemoteDataSource
import com.example.pogoda_mim.data.remote.WeatherResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel(){
    var _item: MutableLiveData<WeatherResponse> = MutableLiveData()
    val item: LiveData<WeatherResponse> get() = _item
    var _hoursItems: MutableLiveData<List<ForecastList>> = MutableLiveData()
    val hoursItems: LiveData<List<ForecastList>> get() = _hoursItems
    var _dailyItems: MutableLiveData<List<DailyForecastList>> = MutableLiveData()
    val dailyItems: LiveData<List<DailyForecastList>> get() = _dailyItems

    private var viewModelJob = Job()
    private val corountineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getData(): Repository = Repository(RemoteDataSource())
    fun getWeather(q: String, key: String){
        corountineScope.launch {
            val result = getData().getWeather(q, key)
            _item.value = result
        }
    }
    fun getHourlyForcast(q: String, key: String){
        corountineScope.launch {
            _hoursItems.value = getData().getHourlyForecast(q, key).list
        }
    }
    fun getDailyForecast(q: String, key: String){
        corountineScope.launch {
            _dailyItems.value = getData().getDailyForecast(q, key).list
        }
    }

}