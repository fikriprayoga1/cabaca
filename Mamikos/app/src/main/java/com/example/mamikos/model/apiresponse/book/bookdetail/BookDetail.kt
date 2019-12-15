package com.example.mamikos.model.apiresponse.book.bookdetail

data class BookDetail(
    var result: BookDetailResult = BookDetailResult(),
    var success: Boolean = false
)