package com.example.mamikos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mamikos.model.apiresponse.genre.GenreListDetail
import com.example.mamikos.other.jetpack.UserRepository
import com.example.mamikos.other.jetpack.Webservice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MainActivityViewModel: ViewModel() {
    // 1
    private val job = Job()
    // 2
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    // 3
    private val tag = "mamikos_cabaca"
    // 4
    private var userRepository: UserRepository? = null
    // 5
    private val toolbarTitle = MutableLiveData<String>()
    // 6
    private lateinit var genreListDetail: GenreListDetail
    // 7
    private var bookId: Int = 0
    // 8
    private var writerId: Int = 0

    fun init() {
        if (userRepository == null) {
            userRepository = UserRepository(Webservice.create())

        }

    }

    fun getJob(): Job {
        return job
    }

    fun getUIScope(): CoroutineScope {
        return uiScope
    }

    fun getTag(): String {
        return tag
    }

    fun getUserRepository(): UserRepository {
        return userRepository!!
    }

    fun setToolbarTitle(toolbarTitle: String) { this.toolbarTitle.value = toolbarTitle }

    fun getToolbarTitle(): LiveData<String> { return toolbarTitle }

    fun setGenreListDetail(genreListDetail: GenreListDetail) {this.genreListDetail = genreListDetail}

    fun getGenreListDetail(): GenreListDetail { return genreListDetail }

    fun setBookId(bookId: Int) { this.bookId = bookId }

    fun getBookId(): Int { return bookId }

    fun setWriterId(writerId: Int) { this.writerId = writerId }

    fun getWriterId(): Int { return writerId }
}