package com.example.mamikos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mamikos.model.apiresponse.writer.WriterListDetail
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.other.jetpack.UserRepository
import com.example.mamikos.other.recyclerviewadapter.WriterRecyclerViewAdapter
import com.example.mamikos.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class WriterListViewModel : ViewModel() {
    // 1
    private lateinit var userRepository: UserRepository
    // 2
    private lateinit var mainActivity: MainActivity
    // 4
    private val job = Job()
    // 5
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    // 6
    private val writerObjects: MutableList<WriterRecyclerViewAdapter.WriterObject> = ArrayList()
    // 7
    private lateinit var writerObject: WriterRecyclerViewAdapter.WriterObject

    fun init(userRepository: UserRepository, mainActivity: MainActivity) {
        this.userRepository = userRepository
        this.mainActivity = mainActivity

    }

    fun requestWriterList(responseListener: ResponseListener) {
        userRepository.requestWriterList(responseListener)

    }

    fun getJob(): Job {
        return job
    }

    fun getUIScope(): CoroutineScope {
        return uiScope
    }

    fun getMainActivity(): MainActivity {
        return mainActivity
    }

    fun getWriterObjects(): MutableList<WriterRecyclerViewAdapter.WriterObject> {
        return writerObjects
    }

    fun initWriterList(writerList: List<WriterListDetail>) {
        writerObjects.clear()

        for (i in writerList.indices) {
            writerObject = WriterRecyclerViewAdapter.WriterObject(writerList[i])
            writerObjects.add(writerObject)

        }

    }
}
