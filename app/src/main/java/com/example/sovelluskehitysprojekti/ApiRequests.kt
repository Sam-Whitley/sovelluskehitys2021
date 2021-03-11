package com.example.sovelluskehitysprojekti

import com.example.sovelluskehitysprojekti.api.*
import retrofit2.http.*
import retrofit2.Call

interface ApiRequests {
    @GET("/v1/search")
    fun getAuthorRecords(
            @Query("lookfor") lookfor: String?,
            @Query("type") type: String?): Call<RecordList>
}