package com.example.mamikos.model.apiresponse.writer

data class Writer(
    var result: List<WriterListDetail> = listOf(),
    var success: Boolean = false
)