package com.example.mamikos.model

data class RetrofitResponse(
    var isSuccess: Boolean,
    var message: String,
    var responseBody: Any?
)