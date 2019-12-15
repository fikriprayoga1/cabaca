package com.example.mamikos.model.apiresponse.book.bookdetail

data class BookDetailReview(
    var User_by_reviewer_id: BookDetailUserReview = BookDetailUserReview(),
    var id: Int = 0,
    var rating: Int = 0,
    var review: String = "",
    var updated_at: String = "",
    var user_id: Int = 0
)