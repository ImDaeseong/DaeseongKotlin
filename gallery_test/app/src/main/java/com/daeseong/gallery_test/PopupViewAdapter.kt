package com.daeseong.gallery_test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class PopupViewAdapter(private val context: Context) : BaseAdapter() {

    private val list: ArrayList<ImageItem> = ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.grid_item1, parent, false)

        val item = getItem(position)
        val imageView: ImageView = view.findViewById(R.id.ivTitle)
        val textView: TextView = view.findViewById(R.id.tvTitle)

        imageView.setImageBitmap(item.bitmap)
        textView.text = item.title

        return view
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): ImageItem {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun addPhoto(item: ImageItem) {
        list.add(item)
        notifyDataSetChanged()
    }
}
