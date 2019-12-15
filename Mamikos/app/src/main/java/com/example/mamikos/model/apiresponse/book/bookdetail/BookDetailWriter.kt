package com.example.mamikos.model.apiresponse.book.bookdetail

data class BookDetailWriter(
    var User_by_user_id: BookDetailUser = BookDetailUser(),
    var id: Int = 0,
    var status: Any = Any(),
    var user_id: Int = 0
)