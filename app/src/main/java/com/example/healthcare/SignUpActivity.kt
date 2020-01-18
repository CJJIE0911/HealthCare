package com.example.healthcare


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.sign_up.*
import org.json.JSONArray
import org.json.JSONObject


class SignUpActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        buttonSignUp.setOnClickListener {
            var genderInput = 'F'
            var gender: Int = radioGroupGender.checkedRadioButtonId
            if(gender == radioButtonMale.id){
                genderInput = 'M'
            }
            //else if(gender == radioButtonFemale.id){
            //    genderInput = 'F'
            //}

            createUser(User(editTextUserNameSignUp.text.toString(),
                editTextPasswordSignUp.text.toString(),
                editTextEmailSignUp.text.toString(),
                genderInput,
                (editTextWeightSignUp.text.toString()).toFloat(),
                (editTextHeightSignUp.text.toString()).toFloat()))
        }
    }

    private fun createUser(user: User) {
        val url = getString(R.string.url_server) + getString(R.string.url_user_create) + "?username=" + user.username +
                "&password=" + user.password + "&email=" + user.email + "&gender=" + user.gender + "&weight=" + user.weight + "&height=" + user.height

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {response ->
                try {
                    if (response != null) {
                        val success: String = response.get("success").toString()

                        if (success.equals("1")) {
                            Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                            //Add record to user list
                            //userList.add(user)
                            //Explicit Intent
                            val intent = Intent(this, SignInActivity::class.java)
                            //start the second activity. With no return value
                            startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext,"Record not saved",Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    Log.d("Main", "Response: %s".format(e.message.toString()))
                }
            },
            Response.ErrorListener { response ->
                Log.d("Main", "Response: %s".format(response.message.toString()))
            }
        )

        // Access the RequestQueue through your singleton class.
        jsonObjectRequest.tag = TAG
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }
    companion object {
        const val TAG = "com.example.healthcare"
    }
}