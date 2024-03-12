package com.daeseong.paging3_test.fun3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daeseong.paging3_test.R

class ImageAdapter : PagingDataAdapter<String, ImageAdapter.ImageViewHolder>(IMAGE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item3_layout, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(imgUrl: String) {

            val iv1: ImageView = view.findViewById(R.id.iv1)
            val tv1: TextView = view.findViewById(R.id.tv1)

            tv1.text = imgUrl

            Glide.with(view)
                .load(imgUrl)
                .into(iv1)
        }
    }

    companion object {

        private val tag = ImageAdapter::class.java.name

        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}
