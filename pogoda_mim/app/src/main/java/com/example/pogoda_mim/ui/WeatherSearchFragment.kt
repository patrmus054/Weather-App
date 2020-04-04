package com.example.pogoda_mim.ui

import `in`.shadowfax.proswipebutton.ProSwipeButton
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pogoda_mim.R
import com.example.pogoda_mim.data.remote.RemoteDataSource
import kotlinx.android.synthetic.main.fragment_weather_search.*
import kotlinx.coroutines.launch


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
            WeatherViewModel.city = edit_text.text.toString()
                ///if(RemoteDataSource.validCity(WeatherViewModel.city, WeatherViewModel.key))
                replaceFragment(fragment)
            }
        awesome_btn.run {
            awesome_btn.onSwipeListener = ProSwipeButton.OnSwipeListener {
                // user has swiped the btn. Perform your async operation now

                Handler().postDelayed({
                    var bool: Boolean = false
                    WeatherViewModel.corountineScope.launch {bool = RemoteDataSource.validCity(WeatherViewModel.city, WeatherViewModel.key) }

                    // task success! show TICK icon in ProSwipeButton
                    awesome_btn.showResultIcon(bool) // false if task failed
                    WeatherViewModel.city = edit_text.text.toString()
                    val fragment = WeatherFragment.newInstance()
                    replaceFragment(fragment)
                }, 2000)
            }
        }
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
   }
}
class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        handler()
        return null
    }
}