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
import com.example.mamikos.model.apiresponse.book.Book
import com.example.mamikos.model.apiresponse.book.BookListDetail
import com.example.mamikos.model.apiresponse.book.bygenre.BookByGenre
import com.example.mamikos.model.apiresponse.book.bygenre.BookByGenreResult
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.other.recyclerviewadapter.BookByGenreRecyclerViewAdapter
import com.example.mamikos.other.recyclerviewadapter.BookRecyclerViewAdapter
import com.example.mamikos.viewmodel.BookListSelectedViewModel
import kotlinx.android.synthetic.main.book_list_fragment.*
import kotlinx.android.synthetic.main.book_list_selected_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookListSelectedFragment : Fragment() {
    lateinit var bookByGenreAdapter: BookByGenreRecyclerViewAdapter

    companion object {
        fun newInstance() = BookListSelectedFragment()
    }

    private lateinit var viewModel: BookListSelectedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_list_selected_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BookListSelectedViewModel::class.java)

        viewModel.init(
            (activity as MainActivity).viewModel.getUserRepository(),
            (activity as MainActivity)
        )

        initRecyclerView()
        showlist()

    }

    private fun initRecyclerView() {
        bookByGenreAdapter = BookByGenreRecyclerViewAdapter(
            context!!,
            viewModel.getBookByGenreObjects(),
            object : BookByGenreRecyclerViewAdapter.BookByGenreListener {
                override fun itemDetail(bookByGenreResult: BookByGenreResult) {
                    viewModel.getUIScope().launch {
                        withContext(Dispatchers.Default) {
                            (activity as MainActivity).viewModel.setBookId(bookByGenreResult.id)
                            (activity as MainActivity).changeFragment2(
                                R.id.frameLayout_activity_main_1,
                                DetailBookFragment(),
                                viewModel.getUIScope()
                            )

                        }

                        (activity as MainActivity).viewModel.setToolbarTitle("Book Detail")

                    }

                }

            })
        val mLayoutManager = LinearLayoutManager(context)

        recyclerView_book_list_selected_fragment_1.layoutManager = mLayoutManager
        recyclerView_book_list_selected_fragment_1.itemAnimator = DefaultItemAnimator()
        recyclerView_book_list_selected_fragment_1.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView_book_list_selected_fragment_1.adapter = bookByGenreAdapter

    }

    private fun showlist() {
        viewModel.getUIScope().launch(Dispatchers.Default) {
            (activity as MainActivity).startLoading(viewModel.getUIScope())
            viewModel.requestBookList(object : ResponseListener {
                override fun retrofitResponse(retrofitResponse: RetrofitResponse) {
                    viewModel.getUIScope().launch {
                        withContext(Dispatchers.Default) {
                            viewModel.getMainActivity().stopLoading(viewModel.getUIScope())
                            if (retrofitResponse.isSuccess) {
                                val bookByGenreData = retrofitResponse.responseBody as BookByGenre
                                val bookByGenreList = bookByGenreData.result

                                viewModel.initBookByGenreList(bookByGenreList)

                            } else {
                                viewModel.getMainActivity().popUp(retrofitResponse.message, viewModel.getUIScope())
                            }

                        }

                        bookByGenreAdapter.notifyDataSetChanged()

                    }

                }


            }, (activity as MainActivity).viewModel.getGenreListDetail())

        }

    }

    override fun onDestroy() {
        viewModel.getJob().cancel()
        super.onDestroy()
    }

}
