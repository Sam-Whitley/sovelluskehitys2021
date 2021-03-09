package com.example.sovelluskehitysprojekti.api

data class RecordList(
        val records: List<Record>,
        val resultCount: Int,
        val status: String,
)