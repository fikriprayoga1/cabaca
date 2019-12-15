package com.example.mamikos.model.apiresponse.book.bygenre

data class BookByGenreWriter(
    var User_by_user_id: BookByGenreUser = BookByGenreUser(),
    var created_at: String = "",
    var id: Int = 0,
    var kelas: Any = Any(),
    var royalty_id: Any = Any(),
    var schedule_task: String = "",
    var status: Any = Any(),
    var user_id: Int = 0
)