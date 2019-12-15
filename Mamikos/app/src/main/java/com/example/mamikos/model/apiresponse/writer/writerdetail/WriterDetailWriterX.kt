package com.example.mamikos.model.apiresponse.writer.writerdetail

data class WriterDetailWriterX(
    var User_by_user_id: WriterDetailUserX = WriterDetailUserX(),
    var created_at: String = "",
    var id: Int = 0,
    var kelas: Any = Any(),
    var royalty_id: Int = 0,
    var schedule_task: String = "",
    var status: Any = Any(),
    var user_id: Int = 0
)