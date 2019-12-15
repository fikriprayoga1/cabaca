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
import com.example.mamikos.model.apiresponse.genre.Genre
import com.example.mamikos.model.apiresponse.genre.GenreListDetail
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.other.recyclerviewadapter.BookRecyclerViewAdapter
import com.example.mamikos.other.recyclerviewadapter.GenreRecyclerViewAdapter
import com.example.mamikos.viewmodel.BookListViewModel
import kotlinx.android.synthetic.main.book_list_fragment.*
import kotlinx.android.synthetic.main.genre_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookListFragment : Fragment() {
    lateinit var bookAdapter: BookRecyclerViewAdapter

    companion object {
        fun newInstance() = BookListFragment()
    }

    private lateinit var viewModel: BookListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BookListViewModel::class.java)

        viewModel.init(
            (activity as MainActivity).viewModel.getUserRepository(),
            (activity as MainActivity)
        )

        initRecyclerView()
        showlist()

    }

    private fun initRecyclerView() {
        bookAdapter = BookRecyclerViewAdapter(
            context!!,
            viewModel.getBookObjects(),
            object : BookRecyclerViewAdapter.BookListener {
                override fun itemDetail(bookListDetail: BookListDetail) {
                    viewModel.getUIScope().launch {
                        withContext(Dispatchers.Default) {
                            (activity as MainActivity).viewModel.setBookId(bookListDetail.book_id)
                            (activity as MainActivity).changeFragment2(
                                R.id.frameLayout_activity_main_1,
                                DetailBookFragment(),
                                viewModel.getUIScope()
                            )

                        }

                    }

                }

            })
        val mLayoutManager = LinearLayoutManager(context)

        recyclerView_book_list_fragment_1.layoutManager = mLayoutManager
        recyclerView_book_list_fragment_1.itemAnimator = DefaultItemAnimator()
        recyclerView_book_list_fragment_1.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView_book_list_fragment_1.adapter = bookAdapter

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
                                val bookData = retrofitResponse.responseBody as Book
                                val bookList = bookData.result

                                viewModel.initBookList(bookList)

                            } else {
                                viewModel.getMainActivity().popUp(retrofitResponse.message, viewModel.getUIScope())
                            }

                        }

                        bookAdapter.notifyDataSetChanged()

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
