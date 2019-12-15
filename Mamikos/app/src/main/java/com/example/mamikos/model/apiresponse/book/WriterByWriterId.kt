package com.example.mamikos.model.apiresponse.book

data class WriterByWriterId(
    var User_by_user_id: UserByUserId = UserByUserId(),
    var id: Int = 0,
    var user_id: Int = 0
)