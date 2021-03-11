package com.example.sovelluskehitysprojekti.api

data class Record(
    val buildings: List<Building>,
    val formats: List<Format>,
    //val id: String,
    //val images: List<Any>,
    //val languages: List<String>,
    val nonPresenterAuthors: List<NonPresenterAuthor>,
    //val onlineUrls: List<Any>,
    //val presenters: Presenters,
    //val rating: Rating,
    //val series: List<Sery>,
    //val subjects: List<List<String>>,
    val title: String
)