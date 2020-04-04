package com.example.pogoda_mim.data.remote

import android.util.Log
import com.example.pogoda_mim.data.DataSource
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*

class RemoteDataSource :  DataSource {
    companion object{
        val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        val SAMPLE_URL = "https://samples.openweathermap.org/data/2.5/"
        val TAG = "RemoteDataSource"
        suspend fun validCity(q: String, appid: String): Boolean{
            try{
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val service = retrofit.create(WeatherService::class.java)
                val call = service.getCurrentWeatherData(q, appid)
                var result = call.await()
                return true
            }catch ( e: Exception){
                Log.e("Retrofit", e.localizedMessage)
            }

            return false
        }

    }

    override suspend fun getWeather(q: String, appid: String): WeatherResponse {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(q, appid)
        return call.await()
    }
    suspend fun validCity(q: String, appid: String): Boolean{
        try{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(WeatherService::class.java)
            val call = service.getCurrentWeatherData(q, appid)
            var result = call.await()
            return true
        }catch ( e: Exception){
            Log.e("Retrofit", e.localizedMessage)
        }

        return false
    }

    override suspend fun getHourlyForecast(q: String, appid: String): HourlyForecastResponse {
        val retrofit = Retrofit.Builder()
            .baseUrl(SAMPLE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getHourlyForecast(q, appid)
        return call.await()
    }

    override suspend fun getDailyForecast(q: String, appid: String): DailyForecastResponse {
        val retrofit = Retrofit.Builder()
            .baseUrl(SAMPLE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getDailyForecast(q, appid)
        var result = call.await()
        var day: Int = 0

        for( i in result.list){
            val date = Calendar.getInstance()
            date.add(Calendar.DATE, day)
            day += 1
            i.day = date.time.toString().substring(0,19)
        }
        return result
    }
}