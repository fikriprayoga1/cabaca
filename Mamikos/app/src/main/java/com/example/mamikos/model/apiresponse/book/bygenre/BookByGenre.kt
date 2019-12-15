package com.example.mamikos.model.apiresponse.book.bygenre

data class BookByGenre(
    var result: List<BookByGenreResult> = listOf(),
    var success: Boolean = false,
    var test: BookByGenreTest = BookByGenreTest()
)