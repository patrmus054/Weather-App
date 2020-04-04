package com.example.pogoda_mim.data.remote

import android.view.ViewDebug
import com.google.gson.annotations.SerializedName

class DailyForecastResponse {
    @SerializedName("city")
    var city = City()
    @SerializedName("cod")
    var cod: String = ""
    @SerializedName("message")
    var message: Double = 0.0
    @SerializedName("cnt")
    var cnt: Int = 0
    @SerializedName("list")
    var list = ArrayList<DailyForecastList>()

}
class DailyForecastList{
    @SerializedName("dt")
    var dt: Long = 0
    @SerializedName("temp")
    var temp = Temp()
    @SerializedName("pressure")
    var pressure: Float = 0.0F
    @SerializedName("humidity")
    var humidity: Int = 0
    @SerializedName("weather")
    var weather = ArrayList<Weather>()
    @SerializedName("speed")
    var speed: Float = 0.0F
    @SerializedName("deg")
    var deg: Int = 0
    @SerializedName("clouds")
    var clouds: Int = 0
    var day: String = ""
}
class Temp{
    @SerializedName("day")
    var day: Float = 0.0F
    @SerializedName("min")
    var min: Float = 0.0F
    @SerializedName("max")
    var max: Float = 0.0F
    @SerializedName("night")
    var night: Float = 0.0F
    @SerializedName("eve")
    var eve: Float = 0.0F
    @SerializedName("morn")
    var morn: Float = 0.0F
}