package com.example.pogoda_mim.data.remote

import androidx.annotation.MainThread
import com.google.gson.annotations.SerializedName

class WeatherResponse {

    @SerializedName("coord")
    var coord: Coord? = null
    @SerializedName("weather")
    var waether = ArrayList<Weather>()
    @SerializedName("base")
    var base: String = ""
    @SerializedName("main")
    var main: Main? = null
    @SerializedName("visibility")
    var visibility: Int = 0
    @SerializedName("wind")
    var wind: Wind? = null
    @SerializedName("clouds")
    var clouds: Clouds? = null
    @SerializedName("dt")
    var dt: Int = 0
    @SerializedName("sys")
    var sys: Sys? = null
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
    @SerializedName("cod")
    var cod: Int = 0
}
class Coord{
    @SerializedName("lon")
    var lon: Float = 0.0F
    @SerializedName("lat")
    var lat: Float = 0.0F
}
class Weather{
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("main")
    var main: String = ""
    @SerializedName("description")
    var description: String = ""
    @SerializedName("icon")
    var icon: String = ""
}
class Main{
    @SerializedName("temp")
    var temp: Float = 0.0F
    @SerializedName("pressure")
    var pressure: Int = 0
    @SerializedName("humidity")
    var humidity: Int = 0
    @SerializedName("temp_min")
    var tempMin: Float = 0.0F
    @SerializedName("temp_max")
    var tempMax: Float = 0.0F
}
class Wind{
    @SerializedName("speed")
    var speed: Float = 0.0F
    @SerializedName("deg")
    var deg: Float = 0.0F
}
class Clouds{
    @SerializedName("all")
    var all: Int = 0
}
class Sys{
    @SerializedName("type")
    var type: Int = 0
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("message")
    var message: Float = 0.0F
    @SerializedName("country")
    var country: String = ""
    @SerializedName("sunrise")
    var sunrise: Long = 0
    @SerializedName("sunset")
    var sunset: Long = 0
}