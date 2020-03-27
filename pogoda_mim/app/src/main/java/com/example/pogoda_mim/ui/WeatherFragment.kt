package com.example.pogoda_mim.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.pogoda_mim.R
import com.example.pogoda_mim.data.remote.WeatherResponde
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

lateinit var weatherViewModel: WeatherViewModel
class WeatherFragment: Fragment(){
    companion object{
        fun newInstance() = WeatherFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    //private var viewModel: WeatherViewModel = weatherViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weatherViewModel.getWeather("London","456de66f6dc8cc38e82dbae674a857dd")
        tv_fragment.text = weatherViewModel.item.value.toString()
    }

}
