package com.example.sovelluskehitysprojekti

import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sovelluskehitysprojekti.api.RecordList
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

// How to Push Android Studio Project to GitHub?
// https://www.youtube.com/watch?v=-dAr6VnmomM&ab_channel=SimplifiedCoding
// (Project/Git/Add, Project/Git/Commit Directory..., then Project/Git/Repository/Push)

// Finna API Docs
// https://www.kiwi.fi/pages/viewpage.action?pageId=53839221

const val BASE_URL = "https://api.finna.fi"

class MainActivity2 : AppCompatActivity() {

    private var TAG = "Test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(findViewById(R.id.toolbar))
        getCurrentData()
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
    }

    private fun getCurrentData() {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiRequests::class.java)

        api.getAuthorRecords().enqueue(object : Callback<RecordList>{
            override  fun onResponse(call: Call<RecordList>, response: Response<RecordList>) {
                d(TAG, "onResponse: ${response.body()!!}")
                response.body()?.let { showData(it) }
                progressBar.visibility = View.INVISIBLE
            }

            override  fun onFailure(call: Call<RecordList>, t: Throwable) {
                d(TAG, "onFailure: $t")
                progressBar.visibility = View.INVISIBLE
            }
        })
    }

    private fun showData(records: RecordList) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity2)
            adapter = MyAdapter(records)
        }
    }
}