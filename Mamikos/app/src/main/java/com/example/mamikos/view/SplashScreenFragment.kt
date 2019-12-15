package com.example.mamikos.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.mamikos.R
import com.example.mamikos.viewmodel.SplashScreenViewModel
import kotlinx.android.synthetic.main.splash_screen_fragment.*

class SplashScreenFragment : Fragment() {

    companion object {
        fun newInstance() = SplashScreenFragment()
    }

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)

        Glide.with(context!!)
            .load(R.drawable.loading)
            .into(imageView_splash_screen_fragment_1)

    }

}
