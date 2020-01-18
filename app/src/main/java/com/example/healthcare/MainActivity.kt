package com.example.healthcare

import android.content.Context
import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransitionImpl


class MainActivity : AppCompatActivity() {

    lateinit var home:Home
    lateinit var calculator: Calculator
    lateinit var waterNeeded:WaterNeeded
    lateinit var workouts: Workouts
    lateinit var profile: Profile
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var token = getSharedPreferences("loginDetails", Context.MODE_PRIVATE)
        var loginStatus = token.getString("loginStatus","false")

        if (loginStatus == "false") {
            val intent = Intent(this, Sign_inout::class.java)
            startActivity(intent)
        }


        val bottomNav:BottomNavigationView = findViewById(R.id.bottom_navigation)
        //default page
        home= Home()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,home).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
        bottomNav.setOnNavigationItemSelectedListener{menuItem ->
            when (menuItem.itemId){
                R.id.home -> {home = Home()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container,home).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }

                R.id.calculator -> { calculator = Calculator()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container,calculator).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }
                R.id.waterNeeded -> { waterNeeded = WaterNeeded()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container,waterNeeded).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }
                R.id.workouts -> {workouts = Workouts()
                        supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,workouts).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }
                R.id.profile -> {profile = Profile()
                        supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,profile).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }
            }

            true
        }

        //I added this if statement to keep the selected fragment when rotating the device
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                Home()
            ).commit()
        }*/


    }
}
