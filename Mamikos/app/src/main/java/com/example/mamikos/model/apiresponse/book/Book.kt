package com.example.mamikos.model.apiresponse.book

data class Book(
    var result: List<BookListDetail> = listOf(),
    var success: Boolean = false
)