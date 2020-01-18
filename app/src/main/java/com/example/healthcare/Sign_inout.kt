package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_view_profile.*
import kotlinx.android.synthetic.main.activity_sign_inout.*

class Sign_inout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_inout)

        buttonSignIn.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)

            //start the second activity. With no return value
            startActivity(intent)
        }
        buttonSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)

            //start the second activity. With no return value
            startActivity(intent)
        }
    }
}
