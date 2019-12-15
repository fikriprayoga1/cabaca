package com.example.mamikos.model.apiresponse.writer

data class WriterListDetail(
    var Writer_by_user_id: WriterByUserId = WriterByUserId(),
    var count_follower: Int = 0,
    var id: Int = 0,
    var is_following: Boolean = false,
    var name: String = "",
    var photo_url: String = "",
    var user_id: Int = 0,
    var username: String = ""
)