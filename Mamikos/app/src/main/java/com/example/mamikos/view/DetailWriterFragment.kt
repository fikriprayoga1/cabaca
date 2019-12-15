package com.example.mamikos.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.mamikos.R
import com.example.mamikos.model.RetrofitResponse
import com.example.mamikos.model.apiresponse.book.bookdetail.BookDetail
import com.example.mamikos.model.apiresponse.writer.writerdetail.WriterDetail
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.viewmodel.DetailWriterViewModel
import kotlinx.android.synthetic.main.detail_book_fragment.*
import kotlinx.android.synthetic.main.detail_writer_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailWriterFragment : Fragment() {

    companion object {
        fun newInstance() = DetailWriterFragment()
    }

    private lateinit var viewModel: DetailWriterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_writer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailWriterViewModel::class.java)

        viewModel.init(
            (activity as MainActivity).viewModel.getUserRepository(),
            (activity as MainActivity)
        )

        requestWriterDetail()

    }

    private fun requestWriterDetail() {
        viewModel.getUIScope().launch(Dispatchers.Default) {
            (activity as MainActivity).startLoading(viewModel.getUIScope())
            viewModel.requestWriterDetail(object : ResponseListener {
                override fun retrofitResponse(retrofitResponse: RetrofitResponse) {
                    viewModel.getUIScope().launch {
                        val rb = retrofitResponse.responseBody as WriterDetail
                        val mWriterDetail = rb.result

                        val imageUrl =
                            "https://cabaca.id:8443/api/v2/files/" + mWriterDetail.photo_url + "&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948"
                        Glide.with(viewModel.getMainActivity()).load(imageUrl).into(circleImageView_detail_writter_fragment_1_1)

                        Log.d((activity as MainActivity).viewModel.getTag(), "DetailWriterFragment/65 : ${mWriterDetail.name}")
                        textView_detail_writer_fragment_1_3.text = mWriterDetail.name
                        textView_detail_writer_fragment_1_5.text = mWriterDetail.email
                        textView_detail_writer_fragment_1_7.text = mWriterDetail.phone
                        textView_detail_writer_fragment_1_9.text = mWriterDetail.birthday
                        justifiedTextView_detail_writer_fragment_1_11.text = mWriterDetail.deskripsi

                        viewModel.getMainActivity().stopLoading(viewModel.getUIScope())

                    }

                }


            }, (activity as MainActivity).viewModel.getWriterId())

        }

    }

}
