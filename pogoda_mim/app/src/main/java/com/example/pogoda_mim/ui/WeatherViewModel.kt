package com.example.pogoda_mim.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pogoda_mim.Repository
import com.example.pogoda_mim.data.remote.RemoteDataSource
import com.example.pogoda_mim.data.remote.WeatherResponde
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel(){
    var _item: MutableLiveData<WeatherResponde> = MutableLiveData()
    val item: LiveData<WeatherResponde> get() = _item

    private var viewModelJob = Job()
    private val corountineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getData(): Repository = Repository(RemoteDataSource())
    fun getWeather(q: String, key: String){
        corountineScope.launch {
            val result = getData().getWeather(q, key)
            _item.value = result
        }
    }

}