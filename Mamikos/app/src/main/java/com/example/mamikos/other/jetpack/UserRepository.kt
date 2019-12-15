package com.example.mamikos.other.jetpack

import android.util.Log
import com.example.mamikos.model.RetrofitResponse
import com.example.mamikos.model.apiresponse.book.Book
import com.example.mamikos.model.apiresponse.book.bookdetail.BookDetail
import com.example.mamikos.model.apiresponse.book.bygenre.BookByGenre
import com.example.mamikos.model.apiresponse.genre.Genre
import com.example.mamikos.model.apiresponse.writer.Writer
import com.example.mamikos.model.apiresponse.writer.writerdetail.WriterDetail
import com.example.mamikos.other.ResponseListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val webservice: Webservice) {
    private val tag = "football_match_schedule"
    private val api_key_header = "25e0bf00ab2fa7f03a9fa57035139e47ccb28c20658f6de907b8011347e369fb"

    fun requestGenreList(responseListener: ResponseListener) {
        var isSuccess = false
        var message: String

        webservice.requestGenreList(api_key_header).enqueue(object : Callback<Genre> {
            override fun onFailure(call: Call<Genre>, t: Throwable) {
                GlobalScope.launch(Dispatchers.Default) {
                    Log.d(tag, "UserRepository/27 : ${t.message}")
                    Log.d(tag, "UserRepository/28 : ${t.cause}")
                    responseListener.retrofitResponse(
                        RetrofitResponse(
                            isSuccess,
                            "onFailure, requestGenreList, UserRepository",
                            null
                        )
                    )

                }

            }

            override fun onResponse(call: Call<Genre>, response: Response<Genre>) {
                val rb = response.body()

                if (rb != null) {
                    isSuccess = true
                    message = "Request response is OK"

                } else { message = "Response body is null" }

                responseListener.retrofitResponse(
                        RetrofitResponse(
                            isSuccess,
                            message,
                            rb
                        )
                    )

            }


        })

    }

    fun requestBookList(responseListener: ResponseListener) {
        var isSuccess = false
        var message: String

        webservice.requestBookList(api_key_header).enqueue(object : Callback<Book> {
            override fun onFailure(call: Call<Book>, t: Throwable) {
                GlobalScope.launch(Dispatchers.Default) {
                    Log.d(tag, "UserRepository/72 : ${t.message}")
                    Log.d(tag, "UserRepository/73 : ${t.cause}")
                    responseListener.retrofitResponse(
                        RetrofitResponse(
                            isSuccess,
                            "onFailure, requestBookList, UserRepository",
                            null
                        )
                    )

                }

            }

            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                val rb = response.body()

                if (rb != null) {
                    isSuccess = true
                    message = "Request response is OK"

                } else { message = "Response body is null" }

                responseListener.retrofitResponse(
                    RetrofitResponse(
                        isSuccess,
                        message,
                        rb
                    )
                )

            }


        })

    }

    fun requestWriterList(responseListener: ResponseListener) {
        var isSuccess = false
        var message: String

        webservice.requestWriterList(api_key_header).enqueue(object : Callback<Writer> {
            override fun onFailure(call: Call<Writer>, t: Throwable) {
                GlobalScope.launch(Dispatchers.Default) {
                    Log.d(tag, "UserRepository/117 : ${t.message}")
                    Log.d(tag, "UserRepository/118 : ${t.cause}")
                    responseListener.retrofitResponse(
                        RetrofitResponse(
                            isSuccess,
                            "onFailure, requestWriterList, UserRepository",
                            null
                        )
                    )

                }

            }

            override fun onResponse(call: Call<Writer>, response: Response<Writer>) {
                val rb = response.body()

                if (rb != null) {
                    isSuccess = true
                    message = "Request response is OK"

                } else { message = "Response body is null" }

                responseListener.retrofitResponse(
                    RetrofitResponse(
                        isSuccess,
                        message,
                        rb
                    )
                )

            }


        })

    }

    fun requestBookByGenre(responseListener: ResponseListener, genreId: Int) {
        var isSuccess = false
        var message: String

        webservice.requestBookByGenre(api_key_header, genreId).enqueue(object : Callback<BookByGenre> {
            override fun onFailure(call: Call<BookByGenre>, t: Throwable) {
                GlobalScope.launch(Dispatchers.Default) {
                    Log.d(tag, "UserRepository/163 : ${t.message}")
                    Log.d(tag, "UserRepository/164 : ${t.cause}")
                    responseListener.retrofitResponse(
                        RetrofitResponse(
                            isSuccess,
                            "onFailure, requestBookByGenreList, UserRepository",
                            null
                        )
                    )

                }

            }

            override fun onResponse(call: Call<BookByGenre>, response: Response<BookByGenre>) {
                val rb = response.body()

                if (rb != null) {
                    isSuccess = true
                    message = "Request response is OK"

                } else { message = "Response body is null" }

                responseListener.retrofitResponse(
                    RetrofitResponse(
                        isSuccess,
                        message,
                        rb
                    )
                )

            }


        })

    }

    fun requestBookDetail(responseListener: ResponseListener, bookId: Int) {
        var isSuccess = false
        var message: String

        webservice.requestBookDetail(api_key_header, bookId).enqueue(object : Callback<BookDetail> {
            override fun onFailure(call: Call<BookDetail>, t: Throwable) {
                GlobalScope.launch(Dispatchers.Default) {
                    Log.d(tag, "UserRepository/209 : ${t.message}")
                    Log.d(tag, "UserRepository/210 : ${t.cause}")
                    responseListener.retrofitResponse(
                        RetrofitResponse(
                            isSuccess,
                            "onFailure, requestBookDetail, UserRepository",
                            null
                        )
                    )

                }

            }

            override fun onResponse(call: Call<BookDetail>, response: Response<BookDetail>) {
                val rb = response.body()

                if (rb != null) {
                    isSuccess = true
                    message = "Request response is OK"

                } else { message = "Response body is null" }

                responseListener.retrofitResponse(
                    RetrofitResponse(
                        isSuccess,
                        message,
                        rb
                    )
                )

            }


        })

    }

    fun requestWriterDetail(responseListener: ResponseListener, writerId: Int) {
        var isSuccess = false
        var message: String

        webservice.requestWriterDetail(api_key_header, writerId).enqueue(object : Callback<WriterDetail> {
            override fun onFailure(call: Call<WriterDetail>, t: Throwable) {
                GlobalScope.launch(Dispatchers.Default) {
                    Log.d(tag, "UserRepository/255 : ${t.message}")
                    Log.d(tag, "UserRepository/256 : ${t.cause}")
                    responseListener.retrofitResponse(
                        RetrofitResponse(
                            isSuccess,
                            "onFailure, requestWriterDetail, UserRepository",
                            null
                        )
                    )

                }

            }

            override fun onResponse(call: Call<WriterDetail>, response: Response<WriterDetail>) {
                val rb = response.body()

                if (rb != null) {
                    isSuccess = true
                    message = "Request response is OK"

                } else { message = "Response body is null" }

                responseListener.retrofitResponse(
                    RetrofitResponse(
                        isSuccess,
                        message,
                        rb
                    )
                )

            }


        })

    }

}