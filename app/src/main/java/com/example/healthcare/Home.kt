package com.example.healthcare

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject


class Home : Fragment() {
    private val REQUEST_CODE = 1
    lateinit var progress: ProgressBar
    lateinit var newsList: ArrayList<News>
    lateinit var adapter: NewsListAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val view: View = inflater.inflate(R.layout.activity_home, container, false)

//Initialise variables and UI
        newsList = ArrayList<News>()

        adapter = NewsListAdapter(activity!!.applicationContext)
        adapter.setNews(newsList)

        progress = view.findViewById(R.id.progress_bar)
        progress.visibility = View.GONE

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        displayNews()
        return view
    }
    private fun displayNews() {
        //Display News
        val url = getString(R.string.url_server) + getString(R.string.url_news_read)
        //Display progress bar
        progress.visibility = View.VISIBLE
        //Delete all record
        //newsList.clear()
        val jsonObjectRequest =
            JsonObjectRequest(
                Request.Method.GET, url, null, Response.Listener { response ->
                //Process the JSON
                try {
                    if (response != null) {
                        val strResponse = response.toString()
                        val jsonResponse = JSONObject(strResponse)
                        val jsonArray: JSONArray = jsonResponse.getJSONArray("records")

                        val size: Int = jsonArray.length()

                        for (i in 0..size - 1) {
                            var jsonNews: JSONObject = jsonArray.getJSONObject(i)
                            var news: News = News(
                                jsonNews.getInt("id"),
                                jsonNews.getString("imageUrl"),
                                jsonNews.getString("title"),
                                jsonNews.getString("description"),
                                jsonNews.getString("author"),
                                jsonNews.getString("publishedTime")
                            )

                            newsList.add(news)
                        }


                        progress.visibility = View.GONE
                    }
                } catch (e: Exception) {
                    Log.d("Main", "Response: %s".format(e.message.toString()))
                }
            },
                Response.ErrorListener { error ->
                    Log.d("Main", "Response: %s".format(error.message.toString()))
                    progress.visibility = View.GONE
                })
        //Volley request policy, only one time request
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            0, //no retry
            1f
        )

        // Access the RequestQueue through your singleton class.
        jsonObjectRequest.tag = TAG
        MySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(jsonObjectRequest)
    }
    /*override fun onBackPressed() {
        MySingleton.getInstance(activity!!.applicationContext).cancelRequest(TAG)
        super.onBackPressed()
    }

    private fun isConnected(): Boolean{
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }*/

    companion object{
        const val TAG = "com.example.healthcare"
    }
}

