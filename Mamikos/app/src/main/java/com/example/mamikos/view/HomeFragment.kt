package com.example.mamikos.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.mamikos.R
import com.example.mamikos.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        (activity as MainActivity).viewModel.getToolbarTitle().observe(this, Observer {
            if (it != null) {
                textView_fragment_home_1_1.text = it

            }

        })

        bottomNavigationView_home_fragment_3.setOnNavigationItemSelectedListener { p0 ->
            var fragment: Fragment = GenreListFragment()

            when (p0.itemId) {
                R.id.item_bottomnavigation_manu_1 -> {
                    fragment = GenreListFragment()
                    (activity as MainActivity).viewModel.setToolbarTitle("Genre")

                }
                R.id.item_bottomnavigation_manu_2 -> {
                    fragment = BookListFragment()
                    (activity as MainActivity).viewModel.setToolbarTitle("Buku")

                }
                R.id.item_bottomnavigation_manu_3 -> {
                    fragment = WriterListFragment()
                    (activity as MainActivity).viewModel.setToolbarTitle("Penulis")

                }
            }

            (activity as MainActivity).changeFragment0(R.id.frameLayout_fragment_home_2, fragment, viewModel.getUIScope())

            true
        }
        bottomNavigationView_home_fragment_3.selectedItemId = R.id.item_bottomnavigation_manu_1
    }

    override fun onDestroy() {
        viewModel.getJob().cancel()
        super.onDestroy()
    }

}
