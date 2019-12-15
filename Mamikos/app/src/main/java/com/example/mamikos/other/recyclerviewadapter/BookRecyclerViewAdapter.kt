package com.example.mamikos.other.recyclerviewadapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mamikos.R
import com.example.mamikos.model.apiresponse.book.BookListDetail
import com.example.mamikos.model.apiresponse.genre.GenreListDetail

class BookRecyclerViewAdapter(
    internal var context: Context
    , private var bookObject: List<BookObject>
    , private var bookListener: BookListener
) : RecyclerView.Adapter<BookRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mImage: ImageView = view.findViewById(R.id.imageView_item_book_1)
        var mTitle: TextView = view.findViewById(R.id.textView_item_book_2)
        var mWriter: TextView = view.findViewById(R.id.textView_item_book_3)
        var mRating: RatingBar = view.findViewById(R.id.ratingBar_item_book_4)

    }

    // this method for build view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLayout = R.layout.item_book
        val itemView = LayoutInflater.from(parent.context)
            .inflate(itemLayout, parent, false)
        return MyViewHolder(itemView)
    }

    // this method for init item in every view item
    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mBookListDetail = bookObject[position].bookListDetail

        val imageUrl =
            "https://cabaca.id:8443/api/v2/files/" + mBookListDetail.cover_url + "&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948"
        Glide.with(context).load(imageUrl).into(holder.mImage)

        holder.mTitle.text = mBookListDetail.title
        holder.mWriter.text = mBookListDetail.Writer_by_writer_id.User_by_user_id.name
        holder.mRating.rating = mBookListDetail.rate_sum.toFloat()

        holder.itemView.setOnClickListener { bookListener.itemDetail(mBookListDetail) }

    }

    // this method for get total item
    override fun getItemCount(): Int {
        var itemCount = 0

        try {
            val itemSize = bookObject.size
            itemCount = itemSize
        } catch (e: Exception) {
        }

        return itemCount
    }

    // this interface for handle more button pressed
    interface BookListener {
        fun itemDetail(bookListDetail: BookListDetail)

    }

    // this class is object of item in recyclerview
    class BookObject(var bookListDetail: BookListDetail)

}