package com.example.mamikos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mamikos.model.apiresponse.book.bookdetail.BookDetailResult
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.other.jetpack.UserRepository
import com.example.mamikos.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class DetailBookViewModel : ViewModel() {
    // 1
    private lateinit var userRepository: UserRepository
    // 2
    private lateinit var mainActivity: MainActivity
    // 4
    private val job = Job()
    // 5
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    // 6
    private lateinit var bookDetailResult: BookDetailResult

    fun init(userRepository: UserRepository, mainActivity: MainActivity) {
        this.userRepository = userRepository
        this.mainActivity = mainActivity

    }

    fun requestBookDetail(responseListener: ResponseListener, bookId: Int) {
        userRepository.requestBookDetail(responseListener, bookId)

    }

    fun getJob(): Job { return job }

    fun getUIScope(): CoroutineScope { return uiScope }

    fun getMainActivity(): MainActivity {
        return mainActivity
    }

    fun setBookDetailResult(bookDetailResult: BookDetailResult) { this.bookDetailResult = bookDetailResult }

    fun getBookDetailResult(): BookDetailResult { return bookDetailResult }

}
