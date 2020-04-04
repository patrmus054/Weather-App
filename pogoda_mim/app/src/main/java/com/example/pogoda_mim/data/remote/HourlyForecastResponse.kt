package com.example.pogoda_mim.data.remote

import com.google.gson.annotations.SerializedName

class HourlyForecastResponse {
    @SerializedName("cod")
    var cod: String = ""
    @SerializedName("message")
    var message: Double = 0.0
    @SerializedName("cnt")
    var cnt: Int = 0
    @SerializedName("list")
    var list = ArrayList<ForecastList>()
    @SerializedName("city")
    var city = City()
    @SerializedName("country")
    var country: String = ""
    @SerializedName("population")
    var population: Long = 0
}
class ForecastList{
    @SerializedName("dt")
    var dt: Long = 0
    @SerializedName("main")
    var main = MainForecast()
    @SerializedName("weather")
    var weather = ArrayList<WeatherForecast>()
    @SerializedName("clouds")
    var clouds = Clouds()
    @SerializedName("wind")
    var wind = Wind()
    @SerializedName("sys")
    var sys = SysHourly()
    @SerializedName("dt_txt")
    var dtTxt: String = ""
}
class SysHourly{
    @SerializedName("pod")
    var pod: String = ""
}
class MainForecast{
    @SerializedName("temp")
    var temp: Float = 0.0F
    @SerializedName("temp_min")
    var tempMin: Float = 0.0F
    @SerializedName("temp_max")
    var tempMax: Float = 0.0F
    @SerializedName("pressure")
    var pressure: Float = 0.0F
    @SerializedName("sea_level")
    var sea_level: Float = 0.0F
    @SerializedName("grnd_level")
    var grnd_level: Float = 0.0F
    @SerializedName("humidity")
    var humidity: Int = 0
    @SerializedName("temp_kf")
    var tempKf: Float = 0.0F
}
class WeatherForecast{
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("main")
    var main: String = ""
    @SerializedName("description")
    var description: String = ""
    @SerializedName("icon")
    var icon: String = ""
}
class City{
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("name")
    var name: String = ""
    @SerializedName("coord")
    var coord = Coord()
    @SerializedName("country")
    var coutry: String = ""
    @SerializedName("population")
    var population: Long = 0
}