package com.example.mamikos.other

import com.example.mamikos.model.RetrofitResponse

interface ResponseListener {
    fun retrofitResponse(retrofitResponse: RetrofitResponse)
}