package com.example.pogoda_mim

import com.example.pogoda_mim.data.DataSource
import com.example.pogoda_mim.data.remote.DailyForecastResponse
import com.example.pogoda_mim.data.remote.HourlyForecastResponse
import com.example.pogoda_mim.data.remote.WeatherResponse


class Repository(private val dataSource: DataSource): DataSource{
    override suspend fun getWeather(q: String, appid: String): WeatherResponse = dataSource.getWeather(q, appid)
    override suspend fun getHourlyForecast(q: String, appid: String): HourlyForecastResponse = dataSource.getHourlyForecast(q, appid)
    override suspend fun getDailyForecast(q: String, appid: String): DailyForecastResponse = dataSource.getDailyForecast(q, appid)
}