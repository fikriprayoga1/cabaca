package com.example.mamikos.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
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
import com.example.mamikos.model.apiresponse.writer.Writer
import com.example.mamikos.model.apiresponse.writer.WriterListDetail
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.other.recyclerviewadapter.GenreRecyclerViewAdapter
import com.example.mamikos.other.recyclerviewadapter.WriterRecyclerViewAdapter
import com.example.mamikos.viewmodel.WriterListViewModel
import kotlinx.android.synthetic.main.genre_list_fragment.*
import kotlinx.android.synthetic.main.writer_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WriterListFragment : Fragment() {
    lateinit var writerAdapter: WriterRecyclerViewAdapter

    companion object {
        fun newInstance() = WriterListFragment()
    }

    private lateinit var viewModel: WriterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.writer_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WriterListViewModel::class.java)

        viewModel.init(
            (activity as MainActivity).viewModel.getUserRepository(),
            (activity as MainActivity)
        )

        initRecyclerView()
        showlist()

    }

    private fun initRecyclerView() {
        writerAdapter = WriterRecyclerViewAdapter(
            context!!,
            viewModel.getWriterObjects(),
            object : WriterRecyclerViewAdapter.WriterListener {
                override fun itemDetail(writerListDetail: WriterListDetail) {
                    viewModel.getUIScope().launch {
                        withContext(Dispatchers.Default) {
                            Log.d((activity as MainActivity).viewModel.getTag(), "WriterListFragment/68 : ${writerListDetail.user_id}")
                            (activity as MainActivity).viewModel.setWriterId(writerListDetail.user_id)
                            (activity as MainActivity).changeFragment2(
                                R.id.frameLayout_activity_main_1,
                                DetailWriterFragment(),
                                viewModel.getUIScope()
                            )

                        }

                    }

                }

            })
        val mLayoutManager = LinearLayoutManager(context)

        recyclerView_writer_list_fragment_1.layoutManager = mLayoutManager
        recyclerView_writer_list_fragment_1.itemAnimator = DefaultItemAnimator()
        recyclerView_writer_list_fragment_1.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView_writer_list_fragment_1.adapter = writerAdapter

    }

    private fun showlist() {
        viewModel.getUIScope().launch(Dispatchers.Default) {
            (activity as MainActivity).startLoading(viewModel.getUIScope())
            viewModel.requestWriterList(object : ResponseListener {
                override fun retrofitResponse(retrofitResponse: RetrofitResponse) {
                    viewModel.getUIScope().launch {
                        withContext(Dispatchers.Default) {
                            viewModel.getMainActivity().stopLoading(viewModel.getUIScope())
                            if (retrofitResponse.isSuccess) {
                                val writerData = retrofitResponse.responseBody as Writer
                                val writerList = writerData.result

                                viewModel.initWriterList(writerList)

                            } else {
                                viewModel.getMainActivity().popUp(retrofitResponse.message, viewModel.getUIScope())
                            }

                        }

                        writerAdapter.notifyDataSetChanged()

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
