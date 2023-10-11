package com.daeseong.gallery_test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ImageViewAdapter(context: Context, resourceID: Int, private val itemList: ArrayList<ImageItem>, private val isGridItem: Boolean) : ArrayAdapter<ImageItem>(context, resourceID, itemList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val v = convertView ?: LayoutInflater.from(context).inflate(
            if (isGridItem) R.layout.grid_item else R.layout.grid_item1,
            parent, false
        )

        val imageView = v.findViewById<ImageView>(R.id.ivTitle)
        val textView = v.findViewById<TextView>(R.id.tvTitle)

        val item = getItem(position)
        imageView.setImageBitmap(item?.bitmap)
        textView.text = item?.title

        return v
    }

    fun addPhoto(item: ImageItem) {
        itemList.add(item)
        notifyDataSetChanged()
    }
}
