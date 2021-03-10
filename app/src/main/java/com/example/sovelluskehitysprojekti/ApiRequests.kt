package com.example.sovelluskehitysprojekti

import android.content.Context
import android.content.SharedPreferences
import com.example.sovelluskehitysprojekti.api.*
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {
    @GET("/v1/search?lookfor=sibelius&type=Author")
    fun getAuthorRecords(): Call<RecordList>
}