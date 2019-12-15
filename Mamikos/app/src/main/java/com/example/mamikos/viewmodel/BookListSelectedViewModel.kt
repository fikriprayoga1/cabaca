package com.example.mamikos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mamikos.model.apiresponse.book.BookListDetail
import com.example.mamikos.model.apiresponse.book.bygenre.BookByGenreResult
import com.example.mamikos.model.apiresponse.genre.GenreListDetail
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.other.jetpack.UserRepository
import com.example.mamikos.other.recyclerviewadapter.BookByGenreRecyclerViewAdapter
import com.example.mamikos.other.recyclerviewadapter.BookRecyclerViewAdapter
import com.example.mamikos.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class BookListSelectedViewModel : ViewModel() {
    // 1
    private lateinit var userRepository: UserRepository
    // 2
    private lateinit var mainActivity: MainActivity
    // 4
    private val job = Job()
    // 5
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    // 6
    private val bookByGenreObjects: MutableList<BookByGenreRecyclerViewAdapter.BookByGenreObject> = ArrayList()
    // 7
    private lateinit var bookByGenreObject: BookByGenreRecyclerViewAdapter.BookByGenreObject

    fun init(userRepository: UserRepository, mainActivity: MainActivity) {
        this.userRepository = userRepository
        this.mainActivity = mainActivity

    }

    fun requestBookList(responseListener: ResponseListener, genreListDetail: GenreListDetail) {
        userRepository.requestBookByGenre(responseListener, genreListDetail.id)

    }

    fun getJob(): Job { return job }

    fun getUIScope(): CoroutineScope { return uiScope }

    fun getMainActivity(): MainActivity {
        return mainActivity
    }

    fun getBookByGenreObjects(): MutableList<BookByGenreRecyclerViewAdapter.BookByGenreObject> {
        return bookByGenreObjects
    }

    fun initBookByGenreList(bookByGenreList: List<BookByGenreResult>) {
        bookByGenreObjects.clear()

        for (i in bookByGenreList.indices) {
            bookByGenreObject = BookByGenreRecyclerViewAdapter.BookByGenreObject(bookByGenreList[i])
            bookByGenreObjects.add(bookByGenreObject)

        }

    }
}
