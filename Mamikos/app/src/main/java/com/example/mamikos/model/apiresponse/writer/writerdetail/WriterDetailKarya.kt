package com.example.mamikos.model.apiresponse.writer.writerdetail

data class WriterDetailKarya(
    var Writer_by_writer_id: WriterDetailWriter = WriterDetailWriter(),
    var bab_count: Int = 0,
    var chapter_count: Int = 0,
    var cover_url: String = "",
    var created_at: String = "",
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