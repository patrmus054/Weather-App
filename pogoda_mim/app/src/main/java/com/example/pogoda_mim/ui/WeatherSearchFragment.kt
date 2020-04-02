package com.example.pogoda_mim.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pogoda_mim.R
import kotlinx.android.synthetic.main.fragment_weather_search.*


class WeatherSearchFragment: Fragment() {
        companion object{
            fun newInstance() = WeatherSearchFragment()
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_weather_search, container, false)

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        normal_btn.setOnClickListener{
                val fragment = WeatherFragment.newInstance()
               replaceFragment(fragment)
            }
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
   }
}