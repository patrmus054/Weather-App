package com.example.pogoda_mim.ui

import `in`.shadowfax.proswipebutton.ProSwipeButton
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pogoda_mim.R
import com.example.pogoda_mim.data.remote.RemoteDataSource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.util.*

class WeatherActivity : AppCompatActivity() {

    private var viewModelJob = Job()
    private val corountineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//    companion object{
//        lateinit var viewModel: WeatherViewModel
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        awesome_btn.setOnSwipeListene(
//            ProSwipeButton.OnSwipeListener {
//                // user has swiped the btn. Perform your async operation now
//                Handler().postDelayed(Runnable {
//                    // task success! show TICK icon in ProSwipeButton
//                    awesome_btn.showResultIcon(true) // false if task failed
//                    val fragment = WeatherFragment.newInstance()
//                    replaceFragment(fragment)
//                }, 2000)
//            }
//        )

        var calendar: Calendar = Calendar.getInstance()

        toolbar.title = ""
        toolbar_title.text = calendar.time.toString()
        setSupportActionBar(toolbar)
        replaceFragment(WeatherSearchFragment.newInstance())
    }



    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

}

