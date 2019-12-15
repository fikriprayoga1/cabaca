package com.example.mamikos.model.apiresponse.book.bygenre

data class BookByGenreResult(
    var Genre_by_genre_id: BookByGenreGenre = BookByGenreGenre(),
    var Writer_by_writer_id: BookByGenreWriter = BookByGenreWriter(),
    var book_url: String = "",
    var chapter_count: Int = 0,
    var cover_url: String = "",
    var created_at: String = "",
    var genre_id: Int = 0,
    var id: Int = 0,
    var isNew: Boolean = false,
    var is_update: Boolean = false,
    var rate_sum: Double = 0.0,
    var schedule_task: String = "",
    var status: String = "",
    var title: String = "",
    var view_count: Int = 0,
    var writer_id: Int = 0
)