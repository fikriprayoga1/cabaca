package com.example.mamikos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.other.jetpack.UserRepository
import com.example.mamikos.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class DetailWriterViewModel : ViewModel() {
    // 1
    private lateinit var userRepository: UserRepository
    // 2
    private lateinit var mainActivity: MainActivity
    // 4
    private val job = Job()
    // 5
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    fun init(userRepository: UserRepository, mainActivity: MainActivity) {
        this.userRepository = userRepository
        this.mainActivity = mainActivity

    }

    fun requestWriterDetail(responseListener: ResponseListener, writerId: Int) {
        userRepository.requestWriterDetail(responseListener, writerId)

    }

    fun getJob(): Job { return job }

    fun getUIScope(): CoroutineScope { return uiScope }

    fun getMainActivity(): MainActivity {
        return mainActivity
    }
}
