package com.example.sovelluskehitysprojekti

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

// How to Push Android Studio Project to GitHub?
// https://www.youtube.com/watch?v=-dAr6VnmomM&ab_channel=SimplifiedCoding
// (Project/Git/Add, Project/Git/Commit Directory..., then Project/Git/Repository/Push)

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
                    val data = response.body()!! //DEBUG POINT

                    val stringBuilder = "Title: " +
                            data.records[1].title + "\nAuthor: " +
                            data.records[1].nonPresenterAuthors[0].name

                    Log.d(TAG, data.toString())

                    withContext(Dispatchers.Main){
                        val textView = findViewById<TextView>(R.id.textView)
                        textView.text = stringBuilder
                        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                        progressBar.visibility = View.INVISIBLE
                    }

                }
            }
    }
}