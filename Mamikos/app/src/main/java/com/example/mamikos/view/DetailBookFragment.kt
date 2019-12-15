package com.example.mamikos.view

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.mamikos.R
import com.example.mamikos.model.RetrofitResponse
import com.example.mamikos.model.apiresponse.book.Book
import com.example.mamikos.model.apiresponse.book.bookdetail.BookDetail
import com.example.mamikos.other.ResponseListener
import com.example.mamikos.viewmodel.DetailBookViewModel
import kotlinx.android.synthetic.main.detail_book_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailBookFragment : Fragment() {

    companion object {
        fun newInstance() = DetailBookFragment()
    }

    private lateinit var viewModel: DetailBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_book_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailBookViewModel::class.java)

        viewModel.init(
            (activity as MainActivity).viewModel.getUserRepository(),
            (activity as MainActivity)
        )

        requestBookDetail()

        button_detail_book_fragment_1_2.setOnClickListener {
            viewModel.getUIScope().launch(Dispatchers.Default) {
                val mBookDetailResult = viewModel.getBookDetailResult()
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mBookDetailResult.url_landing))
                startActivity(browserIntent)

            }

        }

    }

    private fun requestBookDetail() {
        viewModel.getUIScope().launch(Dispatchers.Default) {
            (activity as MainActivity).startLoading(viewModel.getUIScope())
            viewModel.requestBookDetail(object : ResponseListener {
                override fun retrofitResponse(retrofitResponse: RetrofitResponse) {
                    viewModel.getUIScope().launch {
                        val rb = retrofitResponse.responseBody as BookDetail
                        val mBookDetail = rb.result
                        viewModel.setBookDetailResult(mBookDetail)

                        val imageUrl =
                            "https://cabaca.id:8443/api/v2/files/" + mBookDetail.cover_url + "&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948"
                        Glide.with(viewModel.getMainActivity()).load(imageUrl).into(imageView_detail_book_fragment_1_1)

                        textView_detail_book_fragment_1_4.text = mBookDetail.title
                        textView_detail_book_fragment_1_6.text = mBookDetail.Writer_by_writer_id.User_by_user_id.name

                        var genre = ""
                        withContext(Dispatchers.Default) {
                            for (i in mBookDetail.genres.indices) {
                                genre = if (genre == "") {
                                    mBookDetail.genres[i].title
                                } else {
                                    genre + ", " + mBookDetail.genres[i].title

                                }

                            }

                        }
                        textView_detail_book_fragment_1_8.text = genre

                        textView_detail_book_fragment_1_10.text = mBookDetail.view_count.toString()
                        ratingBar_detail_book_fragment_1_12.rating = mBookDetail.decimal_rate.toFloat()
                        justifiedTextView_detail_book_fragment_1_14.text = mBookDetail.synopsis

                        viewModel.getMainActivity().stopLoading(viewModel.getUIScope())

                    }

                }


            }, (activity as MainActivity).viewModel.getBookId())

        }

    }

    override fun onDestroy() {
        viewModel.getJob().cancel()
        super.onDestroy()
    }

}
