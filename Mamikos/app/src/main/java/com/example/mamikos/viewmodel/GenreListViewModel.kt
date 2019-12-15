package com.example.mamikos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mamikos.model.apiresponse.genre.GenreListDetail
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.other.jetpack.UserRepository
import com.example.mamikos.other.recyclerviewadapter.GenreRecyclerViewAdapter
import com.example.mamikos.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class GenreListViewModel : ViewModel() {
    // 1
    private lateinit var userRepository: UserRepository
    // 2
    private lateinit var mainActivity: MainActivity
    // 4
    private val job = Job()
    // 5
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    // 6
    private val genreObjects: MutableList<GenreRecyclerViewAdapter.GenreObject> = ArrayList()
    // 7
    private lateinit var genreObject: GenreRecyclerViewAdapter.GenreObject

    fun init(userRepository: UserRepository, mainActivity: MainActivity) {
        this.userRepository = userRepository
        this.mainActivity = mainActivity

    }

    fun requestGenreList(responseListener: ResponseListener) {
        userRepository.requestGenreList(responseListener)

    }

    fun getJob(): Job { return job }

    fun getUIScope(): CoroutineScope { return uiScope }

    fun getMainActivity(): MainActivity {
        return mainActivity
    }

    fun getGenreObjects(): MutableList<GenreRecyclerViewAdapter.GenreObject> {
        return genreObjects
    }

    fun initGenreList(genreList: List<GenreListDetail>) {
        genreObjects.clear()

        for (i in genreList.indices) {
            genreObject = GenreRecyclerViewAdapter.GenreObject(genreList[i])
            genreObjects.add(genreObject)

        }

    }

}
