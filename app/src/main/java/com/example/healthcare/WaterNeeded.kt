package com.example.healthcare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_water_needed.*
import kotlinx.android.synthetic.main.activity_water_needed.view.*


class WaterNeeded : Fragment() {
    //private var height: EditText? = null
    //private var weight: EditText? = null
    //private var result: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{

        val view: View = inflater.inflate(R.layout.activity_water_needed, container, false)


        view.buttonCalculate.setOnClickListener {
            var waterNeeded: Double = 0.00
            //position = index of an item selected by user
            val age: Int = spinnerAge.selectedItemPosition
            //ID of a radioButton checked by user
            val gender: Int = radioGroupGender.checkedRadioButtonId
            if(gender == radioButtonMale.id){
                waterNeeded += when (age) {
                    0 -> 1.3
                    1 -> 1.7
                    2 -> 2.4
                    3 -> 3.3
                    else -> 3.7
                }
            }
            if(gender == radioButtonFemale.id){
                waterNeeded += when (age) {
                    0 -> 1.3
                    1 -> 1.7
                    2 -> 2.1
                    3 -> 2.3
                    else -> 2.7
                }
            }
            textViewIP.text = getString(R.string.waterNeeded)+waterNeeded +getString(R.string.litre)
        }
        return view
    }

}
