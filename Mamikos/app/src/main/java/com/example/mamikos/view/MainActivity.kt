package com.example.mamikos.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mamikos.R
import com.example.mamikos.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getUIScope().launch(Dispatchers.Default) {
            changeFragment0(R.id.frameLayout_activity_main_1, SplashScreenFragment(), viewModel.getUIScope())
            delay(2000)
            viewModel.init()
            changeFragment0(R.id.frameLayout_activity_main_1, HomeFragment(), viewModel.getUIScope())

        }

    }

    override fun onDestroy() {
        viewModel.getJob().cancel()
        super.onDestroy()
    }

    fun changeFragment0(layout: Int, fragment: Fragment, uiScope: CoroutineScope) {
        uiScope.launch {
            supportFragmentManager
                .beginTransaction()
                .replace(layout, fragment)
                .commitAllowingStateLoss()

        }

    }

    fun changeFragment1(layout: Int, fragment: Fragment, uiScope: CoroutineScope) {
        uiScope.launch {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null) // detect addbackstack
                .replace(layout, fragment)
                .commitAllowingStateLoss()

        }

    }

    fun changeFragment2(layout: Int, fragment: Fragment, uiScope: CoroutineScope) {
        uiScope.launch {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .add(layout, fragment)
                .commitAllowingStateLoss()

        }

    }

    fun changeFragment3(layout: Int, fragment: Fragment, uiScope: CoroutineScope) {
        uiScope.launch {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(layout, fragment)
                .commitAllowingStateLoss()

        }

    }

    fun startLoading(uiScope: CoroutineScope) {
        uiScope.launch { cardView_activity_main_2.visibility = View.VISIBLE }

    }

    fun stopLoading(uiScope: CoroutineScope) {
        uiScope.launch { cardView_activity_main_2.visibility = View.GONE }

    }

    fun popUp(message: String, uiScope: CoroutineScope) {
        uiScope.launch { Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show() }

    }

    fun exitKeyboard(uiScope: CoroutineScope) {
        uiScope.launch {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

    }
}
