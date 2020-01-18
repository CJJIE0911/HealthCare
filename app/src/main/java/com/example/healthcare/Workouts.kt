package com.example.healthcare

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_workouts.view.*


class Workouts : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val view: View= inflater.inflate(R.layout.activity_workouts, container, false)

        view.button.setOnClickListener{
            val intent = Intent(activity!!.applicationContext, StartWorkout::class.java)
            startActivity(intent)
        }
        view.button2.setOnClickListener{
            val intent = Intent(activity!!.applicationContext, StartWorkout::class.java)
            startActivity(intent)
        }
        return view
    }
}
