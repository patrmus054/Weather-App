package com.example.pogoda_mim.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather/")
    fun getCurrentWeatherData(@Query("q")q: String,@Query("appid")appid: String): Call<WeatherResponde>
}