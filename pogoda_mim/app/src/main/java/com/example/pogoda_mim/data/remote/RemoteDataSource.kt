package com.example.pogoda_mim.data.remote

import com.example.pogoda_mim.data.DataSource
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource : DataSource {
    companion object{
        val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        val TAG = "RemoteDataSource"
    }

    override suspend fun getWeather(q: String, appid: String): WeatherResponde {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(q, appid)
        return call.await()
    }


}