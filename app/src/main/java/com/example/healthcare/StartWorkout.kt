package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_start_workout.*

class StartWorkout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_workout)
        // give an event to another page
        fitone.setOnClickListener(View.OnClickListener {
            val a = Intent(this, DoingWorkout::class.java)
            startActivity(a)
        })
        fittwo.setOnClickListener {
            val a = Intent(this, DoingWorkout2::class.java)
            startActivity(a)
        }
        fitthree.setOnClickListener {
            val a = Intent(this, DoingWorkout3::class.java)
            startActivity(a)
        }
        fitfour.setOnClickListener{
            val a = Intent(this, DoingWorkout4::class.java)
            startActivity(a)
        }
        btnBack.setOnClickListener{
            val a = Intent(this, MainActivity::class.java)
            startActivity(a)
        }
    }
}
