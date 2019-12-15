package com.example.mamikos.model.apiresponse.writer.writerdetail

data class WriterDetailResult(
    var Writer_by_user_id: WriterDetailWriterUser = WriterDetailWriterUser(),
    var birthday: String = "",
    var coin: Int = 0,
    var count_follower: Int = 0,
    var count_following: Int = 0,
    var current_level: Int = 0,
    var deskripsi: String = "",
    var email: String = "",
    var following_user: List<Any> = listOf(),
    var gender: String = "",
    var id: Int = 0,
    var is_following: Boolean = false,
    var karya: List<WriterDetailKarya> = listOf(),
    var link_user: String = "",
    var name: String = "",
    var phone: String = "",
    var photo_url: String = "",
    var reading_list: List<WriterDetailReading> = listOf(),
    var username: String = "",
    var writer_id: Int = 0,
    var writer_level: Int = 0,
    var writer_level_name: Any = Any()
)