package com.example.mamikos.model.apiresponse.book.bookdetail

data class BookDetailChapter(
    var book_id: Int = 0,
    var chapter_sequence: Int = 0,
    var created_at: String = "",
    var id: Int = 0,
    var is_purchased: Boolean = false,
    var is_readed: Boolean = false,
    var is_warning: Any = Any(),
    var like_count: Int = 0,
    var new: Boolean = false,
    var price: Int = 0,
    var schedule_task: String = "",
    var title: String = "",
    var view_by_user: Int = 0,
    var view_count: Int = 0
)