package com.example.pogoda_mim.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pogoda_mim.Repository
import com.example.pogoda_mim.data.remote.*
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

    companion object{
        var city: String = ""
        val key: String = "456de66f6dc8cc38e82dbae674a857dd"
        var viewModelJob = Job()
        val corountineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    }


    fun getData(): Repository = Repository(RemoteDataSource())
    fun getWeather(){
        corountineScope.launch {
            val result = getData().getWeather(city,  key)
            _item.value = result
        }
    }
    fun getHourlyForcast(){
        corountineScope.launch {

            _hoursItems.value = getData().getHourlyForecast(city, key).list
        }
    }
    fun getDailyForecast(){
        corountineScope.launch {
            _dailyItems.value = getData().getDailyForecast(city, key).list
            // niestety okazalo sie ze powyzsza linika wymaga uzycia API, ktore jest platne
            // dlatego ponizej ustawiam wartsci "Na twardo"
        }
    }

}

