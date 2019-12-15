package com.example.mamikos.model.apiresponse.writer

data class WriterByUserId(
    var id: Int = 0,
    var schedule_task: String = "",
    var status: Any = Any(),
    var user_id: Int = 0
)