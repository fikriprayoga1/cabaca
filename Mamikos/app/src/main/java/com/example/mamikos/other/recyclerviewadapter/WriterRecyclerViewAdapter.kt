package com.example.mamikos.other.recyclerviewadapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mamikos.R
import com.example.mamikos.model.apiresponse.writer.WriterListDetail
import de.hdodenhof.circleimageview.CircleImageView

class WriterRecyclerViewAdapter(
    internal var context: Context
    , private var writerObject: List<WriterObject>
    , private var writerListener: WriterListener
) : RecyclerView.Adapter<WriterRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mImage: CircleImageView = view.findViewById(R.id.circleImageView_item_book_1)
        var mTitle: TextView = view.findViewById(R.id.textView_item_book_2)
        var mUsername: TextView = view.findViewById(R.id.textView_item_book_3)
        var mFollower: TextView = view.findViewById(R.id.textView_item_book_4)

    }

    // this method for build view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLayout = R.layout.item_writer
        val itemView = LayoutInflater.from(parent.context)
            .inflate(itemLayout, parent, false)
        return MyViewHolder(itemView)
    }

    // this method for init item in every view item
    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mWriterListDetail = writerObject[position].writerListDetail

        val imageUrl =
            "https://cabaca.id:8443/api/v2/files/" + mWriterListDetail.photo_url + "&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948"
        Glide.with(context).load(imageUrl).into(holder.mImage)

        holder.mTitle.text = mWriterListDetail.name
        holder.mUsername.text = mWriterListDetail.username

        val followerCount = mWriterListDetail.count_follower.toString() + " Followers"
        holder.mFollower.text = followerCount

        holder.itemView.setOnClickListener { writerListener.itemDetail(mWriterListDetail) }

    }

    // this method for get total item
    override fun getItemCount(): Int {
        var itemCount = 0

        try {
            val itemSize = writerObject.size
            itemCount = itemSize
        } catch (e: Exception) {
        }

        return itemCount
    }

    // this interface for handle more button pressed
    interface WriterListener {
        fun itemDetail(writerListDetail: WriterListDetail)

    }

    // this class is object of item in recyclerview
    class WriterObject(var writerListDetail: WriterListDetail)

}