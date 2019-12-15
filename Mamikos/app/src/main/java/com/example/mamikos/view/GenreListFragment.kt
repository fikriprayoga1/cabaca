package com.example.mamikos.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mamikos.R
import com.example.mamikos.model.RetrofitResponse
import com.example.mamikos.model.apiresponse.genre.Genre
import com.example.mamikos.model.apiresponse.genre.GenreListDetail
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.other.recyclerviewadapter.GenreRecyclerViewAdapter
import com.example.mamikos.viewmodel.GenreListViewModel
import kotlinx.android.synthetic.main.genre_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenreListFragment : Fragment() {
    lateinit var genreAdapter: GenreRecyclerViewAdapter

    companion object {
        fun newInstance() = GenreListFragment()
    }

    private lateinit var viewModel: GenreListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.genre_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GenreListViewModel::class.java)

        viewModel.init(
            (activity as MainActivity).viewModel.getUserRepository(),
            (activity as MainActivity)
        )

        initRecyclerView()
        showlist()

    }

    private fun initRecyclerView() {
        genreAdapter = GenreRecyclerViewAdapter(
            context!!,
            viewModel.getGenreObjects(),
            object : GenreRecyclerViewAdapter.GenreListener {
                override fun itemDetail(genreListDetail: GenreListDetail) {
                    viewModel.getUIScope().launch {
                        withContext(Dispatchers.Default) {
                            (activity as MainActivity).viewModel.setGenreListDetail(genreListDetail)
                            (activity as MainActivity).changeFragment2(
                                R.id.frameLayout_activity_main_1,
                                BookListSelectedFragment(),
                                viewModel.getUIScope()
                            )

                        }

                    }

                }

            })
        val mLayoutManager = LinearLayoutManager(context)

        recyclerView_genre_list_fragment_1.layoutManager = mLayoutManager
        recyclerView_genre_list_fragment_1.itemAnimator = DefaultItemAnimator()
        recyclerView_genre_list_fragment_1.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView_genre_list_fragment_1.adapter = genreAdapter

    }

    private fun showlist() {
        viewModel.getUIScope().launch(Dispatchers.Default) {
            (activity as MainActivity).startLoading(viewModel.getUIScope())
            viewModel.requestGenreList(object : ResponseListener {
                override fun retrofitResponse(retrofitResponse: RetrofitResponse) {
                    viewModel.getUIScope().launch {
                        withContext(Dispatchers.Default) {
                            viewModel.getMainActivity().stopLoading(viewModel.getUIScope())
                            if (retrofitResponse.isSuccess) {
                                val genreData = retrofitResponse.responseBody as Genre
                                val genreList = genreData.resource

                                viewModel.initGenreList(genreList)

                            } else {
                                viewModel.getMainActivity()
                                    .popUp(retrofitResponse.message, viewModel.getUIScope())
                            }

                        }

                        genreAdapter.notifyDataSetChanged()

                    }

                }


            })

        }

    }

    override fun onDestroy() {

        viewModel.getJob().cancel()
        super.onDestroy()
    }

}
