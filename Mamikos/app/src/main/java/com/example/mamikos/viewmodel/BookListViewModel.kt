package com.example.mamikos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mamikos.model.apiresponse.book.BookListDetail
import com.example.mamikos.model.apiresponse.genre.GenreListDetail
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.other.jetpack.UserRepository
import com.example.mamikos.other.recyclerviewadapter.BookRecyclerViewAdapter
import com.example.mamikos.other.recyclerviewadapter.GenreRecyclerViewAdapter
import com.example.mamikos.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class BookListViewModel : ViewModel() {
    // 1
    private lateinit var userRepository: UserRepository
    // 2
    private lateinit var mainActivity: MainActivity
    // 4
    private val job = Job()
    // 5
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    // 6
    private val bookObjects: MutableList<BookRecyclerViewAdapter.BookObject> = ArrayList()
    // 7
    private lateinit var bookObject: BookRecyclerViewAdapter.BookObject

    fun init(userRepository: UserRepository, mainActivity: MainActivity) {
        this.userRepository = userRepository
        this.mainActivity = mainActivity

    }

    fun requestBookList(responseListener: ResponseListener) {
        userRepository.requestBookList(responseListener)

    }

    fun getJob(): Job { return job }

    fun getUIScope(): CoroutineScope { return uiScope }

    fun getMainActivity(): MainActivity {
        return mainActivity
    }

    fun getBookObjects(): MutableList<BookRecyclerViewAdapter.BookObject> {
        return bookObjects
    }

    fun initBookList(bookList: List<BookListDetail>) {
        bookObjects.clear()

        for (i in bookList.indices) {
            bookObject = BookRecyclerViewAdapter.BookObject(bookList[i])
            bookObjects.add(bookObject)

        }

    }

}
