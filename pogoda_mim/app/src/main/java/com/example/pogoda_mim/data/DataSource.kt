package com.example.pogoda_mim.data

import com.example.pogoda_mim.data.remote.DailyForecastResponse
import com.example.pogoda_mim.data.remote.HourlyForecastResponse
import com.example.pogoda_mim.data.remote.WeatherResponse

interface DataSource {
    suspend fun getWeather(q: String, appid: String): WeatherResponse
    suspend fun getHourlyForecast(q: String, appid: String): HourlyForecastResponse
    suspend fun getDailyForecast(q: String, appid: String): DailyForecastResponse
}