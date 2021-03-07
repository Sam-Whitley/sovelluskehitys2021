package com.example.sovelluskehitysprojekti

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.finna.fi"

class MainActivity2 : AppCompatActivity() {

    private var TAG = "MainActivity2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(findViewById(R.id.toolbar))
        getCurrentData()
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
    }

    private fun getCurrentData() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                val response = api.getAuthorRecords().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    //Log.d(TAG, data.name)

                    withContext(Dispatchers.Main){
                        val textView = findViewById<TextView>(R.id.textView)
                        textView.text = data.role
                        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                        progressBar.visibility = View.INVISIBLE
                    }

                }
            }
    }
}