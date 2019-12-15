package com.example.mamikos.model.apiresponse.writer.writerdetail

data class WriterDetail(
    var result: WriterDetailResult = WriterDetailResult(),
    var success: Boolean = false
)