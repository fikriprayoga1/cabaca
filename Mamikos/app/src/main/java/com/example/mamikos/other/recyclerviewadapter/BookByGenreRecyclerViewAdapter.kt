package com.example.mamikos.other.recyclerviewadapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mamikos.R
import com.example.mamikos.model.apiresponse.book.bygenre.BookByGenreResult

class BookByGenreRecyclerViewAdapter(
    internal var context: Context
    , private var bookByGenreObject: List<BookByGenreObject>
    , private var bookByGenreListener: BookByGenreListener
) : RecyclerView.Adapter<BookByGenreRecyclerViewAdapter.MyViewHolder>() {

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
        val mBookByGenreListDetail = bookByGenreObject[position].bookByGenreResult

        val imageUrl =
            "https://cabaca.id:8443/api/v2/files/" + mBookByGenreListDetail.cover_url + "&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948"
        Glide.with(context).load(imageUrl).into(holder.mImage)

        holder.mTitle.text = mBookByGenreListDetail.title
        holder.mWriter.text = mBookByGenreListDetail.Writer_by_writer_id.User_by_user_id.name
        holder.mRating.rating = mBookByGenreListDetail.rate_sum.toFloat()

        holder.itemView.setOnClickListener { bookByGenreListener.itemDetail(mBookByGenreListDetail) }

    }

    // this method for get total item
    override fun getItemCount(): Int {
        var itemCount = 0

        try {
            val itemSize = bookByGenreObject.size
            itemCount = itemSize
        } catch (e: Exception) {
        }

        return itemCount
    }

    // this interface for handle more button pressed
    interface BookByGenreListener {
        fun itemDetail(bookByGenreResult: BookByGenreResult)

    }

    // this class is object of item in recyclerview
    class BookByGenreObject(var bookByGenreResult: BookByGenreResult)

}