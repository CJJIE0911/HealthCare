package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_doing_workout.*
import java.util.*

class DoingWorkout : AppCompatActivity() {
    private val START_TIME_IN_MILLIS: Long = 300000
    private var countDownTimer: CountDownTimer? = null
    private var mTimerRunning: Boolean = false
    private var mTimeLeftInMillis = START_TIME_IN_MILLIS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doing_workout)
        startTimer()
        // give an event to another page
        buttonDone.setOnClickListener{
            if(countDownTimer != null) {
                countDownTimer!!.cancel()
                countDownTimer = null
            }
            Toast.makeText(applicationContext, "Congratulations!", Toast.LENGTH_SHORT).show()
            val a = Intent(this, StartWorkout::class.java)
            startActivity(a)
        }
    }
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDowntText()
            }

            override fun onFinish() {
                Toast.makeText(applicationContext, "Times Up!", Toast.LENGTH_SHORT).show()
            }
        }.start()
        mTimerRunning = true
    }

    private fun updateCountDowntText() {
        val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60

        val timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        timerValue.text = timeLeft
    }
}
