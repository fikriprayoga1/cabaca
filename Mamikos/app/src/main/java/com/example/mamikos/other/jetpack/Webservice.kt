package com.example.mamikos.other.jetpack

import com.example.mamikos.model.apiresponse.book.Book
import com.example.mamikos.model.apiresponse.book.bookdetail.BookDetail
import com.example.mamikos.model.apiresponse.book.bygenre.BookByGenre
import com.example.mamikos.model.apiresponse.genre.Genre
import com.example.mamikos.model.apiresponse.writer.Writer
import com.example.mamikos.model.apiresponse.writer.writerdetail.WriterDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface Webservice {
    @GET("api/v2/cabaca/_table/genre")
    fun requestGenreList(
        @Header("x-dreamfactory-api-key") header: String
    ): Call<Genre>

    @GET("api/v2/book/uptodate")
    fun requestBookList(
        @Header("x-dreamfactory-api-key") header: String
    ): Call<Book>

    @GET("api/v2/writer/popular")
    fun requestWriterList(
        @Header("x-dreamfactory-api-key") header: String
    ): Call<Writer>

    @GET("api/v2/book/category")
    fun requestBookByGenre(
        @Header("x-dreamfactory-api-key") header: String,
        @Query("id") genreId: Int
    ): Call<BookByGenre>

    @GET("api/v2/book/detail/{id}")
    fun requestBookDetail(
        @Header("x-dreamfactory-api-key") header: String,
        @Path("id") bookId: Int
    ): Call<BookDetail>

    @GET("api/v2/writer/detail/{id}")
    fun requestWriterDetail(
        @Header("x-dreamfactory-api-key") header: String,
        @Path("id") writerId: Int
    ): Call<WriterDetail>

    companion object Factory {
        fun create(): Webservice {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://cabaca.id:8443/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(Webservice::class.java)
        }
    }
}