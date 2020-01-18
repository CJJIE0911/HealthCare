package com.example.healthcare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.sign_up.*

class Edit: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        buttonSignUp.setOnClickListener {
            var genderInput = 'F'
            var gender: Int = radioGroupGender.checkedRadioButtonId
            if(gender == radioButtonMale.id){
                genderInput = 'M'
            }
            //else if(gender == radioButtonFemale.id){
            //    genderInput = 'F'
            //}

            editUser(User(editTextUserNameSignUp.text.toString(),
                editTextPasswordSignUp.text.toString(),
                editTextEmailSignUp.text.toString(),
                genderInput,
                (editTextWeightSignUp.text.toString()).toFloat(),
                (editTextHeightSignUp.text.toString()).toFloat()))
        }
    }
    private fun editUser(user:User){
        val url = getString(R.string.url_server) + getString(R.string.url_edit)

    }
}