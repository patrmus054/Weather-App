package com.example.pogoda_mim.data

import com.example.pogoda_mim.data.remote.WeatherResponde

interface DataSource {
    suspend fun getWeather(q: String, appid: String): WeatherResponde
}