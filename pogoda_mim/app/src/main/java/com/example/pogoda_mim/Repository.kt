package com.example.pogoda_mim

import com.example.pogoda_mim.data.DataSource
import com.example.pogoda_mim.data.remote.WeatherResponde


class Repository(private val dataSource: DataSource): DataSource{
    override suspend fun getWeather(q: String, appid: String): WeatherResponde = dataSource.getWeather(q, appid)
}