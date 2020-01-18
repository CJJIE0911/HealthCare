package com.example.healthcare

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_view_profile.*
import kotlinx.android.synthetic.main.activity_view_profile.view.*
import org.json.JSONObject

class Profile : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{


        val view: View = inflater.inflate(R.layout.activity_view_profile, container, false)

        val token = this.activity!!.getSharedPreferences("loginDetails", Context.MODE_PRIVATE)
        val userName = token.getString("username"," ")
        val password = token.getString("password", " ")


        val url = getString(R.string.url_server) + getString(R.string.url_user_read_one) + "?username=" + userName + "&password=" + password

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // Process the JSON
                try{
                    if(response != null){
                        val strResponse = response.toString()
                        val jsonResponse  = JSONObject(strResponse)

                        textViewUserNameAns.text = jsonResponse.getString("username")
                        textViewGenderAns.text = jsonResponse.getString("gender")
                        textViewEmailAns.text = jsonResponse.getString("email").toString()
                        textViewWeightAns.text = jsonResponse.getString("weight")
                        textViewHeightAns.text = jsonResponse.getString("height")

                        //userList.add(user)

                        //Toast.makeText(applicationContext, "Record found :", Toast.LENGTH_LONG).show()
                        //progress.visibility = View.GONE
                    }
                }catch (e:Exception){
                    Log.d("Main", "Response: %s".format(e.message.toString()))
                    //progress.visibility = View.GONE

                }
            },
            Response.ErrorListener { error ->
                Log.d("Main", "Response: %s".format(error.message.toString()))
                //progress.visibility = View.GONE
            }
        )

        //Volley request policy, only one time request
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            0, //no retry
            1f
        )

        // Access the RequestQueue through your singleton class.
        jsonObjectRequest.tag = TAG
        MySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(jsonObjectRequest)

        view.buttonSignOut.setOnClickListener {
            val intent = Intent(activity!!.applicationContext, MainActivity::class.java)
            var loginDetail = token.edit()
            loginDetail.putString("username","")
            loginDetail.putString("password","")
            loginDetail.putString("loginStatus", "false")
            loginDetail.commit()
            startActivity(intent)
        }
        return view
    }
    companion object{
        const val TAG = "com.example.healthcare"
    }
}
