package com.example.sovelluskehitysprojekti

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sovelluskehitysprojekti.api.RecordList
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

// How to Push Android Studio Project to GitHub?
// https://www.youtube.com/watch?v=-dAr6VnmomM&ab_channel=SimplifiedCoding
// (Project/Git/Add, Project/Git/Commit Directory..., then Project/Git/Repository/Push)

const val BASE_URL = "https://api.finna.fi"

class MainActivity2 : AppCompatActivity() {

    //private var TAG = "MainActivity2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(findViewById(R.id.toolbar))
        getCurrentData()
        //val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        //progressBar.visibility = View.VISIBLE
    }

    private fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiRequests::class.java)

        api.getAuthorRecords().enqueue(object : Callback<RecordList>{
            override  fun onResponse(call: Call<RecordList>, response: Response<RecordList>) {
                //d("Test", "onResponse: ${response.body()!!} ")
                response.body()?.let { showData(it) }
            }

            override  fun onFailure(call: Call<RecordList>, t: Throwable) {
                d("Test", "onFailure: $t")
            }
        })

            /*GlobalScope.launch(Dispatchers.IO) {
                val response = retrofit.getAuthorRecords().awaitResponse()
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
            }*/
    }

    private fun showData(records: RecordList) {
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity2)
            adapter = MyAdapter(records)
        }
    }
}