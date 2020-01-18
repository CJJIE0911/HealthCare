package com.example.healthcare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_calculator.view.*


class Calories : Fragment() {
    //private var height: EditText? = null
    //private var weight: EditText? = null
    //private var result: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{

        val view: View = inflater.inflate(R.layout.activity_calculator, container, false)


        view.buttonCalculateBMI.setOnClickListener {
            //val intent = Intent(activity!!.applicationContext, MainActivity::class.java)
            //startActivity(intent)
            view.result123.text = "asdasd"
            val weightStr  = view.weight.text.toString()
            val heightStr  = view.height123.text.toString()


            if (heightStr != null && "" != heightStr
                && weightStr != null && "" != weightStr
            ) {
                val heightValue = java.lang.Float.parseFloat(heightStr) / 100
                val weightValue = java.lang.Float.parseFloat(weightStr)

                val bmi = weightValue / (heightValue * heightValue)
                var bmiLabel = ""
                if (bmi.compareTo(15f) <= 0) {
                    bmiLabel = getString(R.string.very_severely_underweight)
                } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
                ) {
                    bmiLabel = getString(R.string.severely_underweight)
                } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
                ) {
                    bmiLabel = getString(R.string.underweight)
                } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
                ) {
                    bmiLabel = getString(R.string.normal)
                } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
                ) {
                    bmiLabel = getString(R.string.overweight)
                } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
                ) {
                    bmiLabel = getString(R.string.obese_class_i)
                } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
                ) {
                    bmiLabel = getString(R.string.obese_class_ii)
                } else {
                    bmiLabel = getString(R.string.obese_class_iii)
                }

                bmiLabel = bmi.toString() + "\n\n" + bmiLabel
                view.result123.text = bmiLabel
            }

        }
        return view
    }

   /* private fun displayBMI(bmi: Float) {
        var bmiLabel = ""
        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight)
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = getString(R.string.severely_underweight)
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = getString(R.string.underweight)
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = getString(R.string.normal)
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = getString(R.string.overweight)
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = getString(R.string.obese_class_i)
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = getString(R.string.obese_class_ii)
        } else {
            bmiLabel = getString(R.string.obese_class_iii)
        }

        bmiLabel = bmi.toString() + "\n\n" + bmiLabel
        result!!.text = bmiLabel
    }*/
}
