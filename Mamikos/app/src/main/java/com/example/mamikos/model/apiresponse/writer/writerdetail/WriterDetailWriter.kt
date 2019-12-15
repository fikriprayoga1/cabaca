package com.example.mamikos.model.apiresponse.writer.writerdetail

data class WriterDetailWriter(
    var User_by_user_id: WriterDetailUser = WriterDetailUser(),
    var id: Int = 0,
    var royalty_id: Any = Any(),
    var status: Any = Any(),
    var user_id: Int = 0
)