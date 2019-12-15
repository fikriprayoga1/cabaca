package com.example.mamikos.other.recyclerviewadapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mamikos.R
import com.example.mamikos.model.apiresponse.genre.GenreListDetail

class GenreRecyclerViewAdapter(
    internal var context: Context
    , private var genreObject: List<GenreObject>
    , private var genreListener: GenreListener
) : RecyclerView.Adapter<GenreRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mCategory: TextView = view.findViewById(R.id.textView_item_genre_1)

    }

    // this method for build view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLayout = R.layout.item_genre
        val itemView = LayoutInflater.from(parent.context)
            .inflate(itemLayout, parent, false)
        return MyViewHolder(itemView)
    }

    // this method for init item in every view item
    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mGenreListDetail = genreObject[position].genreListDetail

        holder.mCategory.text = mGenreListDetail.title

        holder.itemView.setOnClickListener { genreListener.itemDetail(mGenreListDetail) }

    }

    // this method for get total item
    override fun getItemCount(): Int {
        var itemCount = 0

        try {
            val itemSize = genreObject.size
            itemCount = itemSize
        } catch (e: Exception) {
        }

        return itemCount
    }

    // this interface for handle more button pressed
    interface GenreListener {
        fun itemDetail(genreListDetail: GenreListDetail)

    }

    // this class is object of item in recyclerview
    class GenreObject(var genreListDetail: GenreListDetail)

}