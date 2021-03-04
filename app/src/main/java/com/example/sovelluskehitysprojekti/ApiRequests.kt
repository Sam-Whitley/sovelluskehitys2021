package com.example.sovelluskehitysprojekti

import com.example.sovelluskehitysprojekti.api.AuthorJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {

    @GET('/v1/search?lookfor=sibelius&type=Author')
    fun getAuthorRecords(): Call<AuthorJson>
}