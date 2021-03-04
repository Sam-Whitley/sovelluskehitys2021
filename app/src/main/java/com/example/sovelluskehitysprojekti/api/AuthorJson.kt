package com.example.sovelluskehitysprojekti.api

data class AuthorJson(
    val records: List<Record>,
    val resultCount: Int,
    val status: String
)