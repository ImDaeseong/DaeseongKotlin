package com.daeseong.gallery_test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class PopupViewAdapter(private val context: Context) : BaseAdapter() {

    private val list : ArrayList<ImageItem> = ArrayList<ImageItem>()

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {

        var v: View? = view

        if (v == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = inflater.inflate(R.layout.grid_item1, viewGroup, false)
        }

        val item = list[i]
        val imageView: ImageView = v!!.findViewById<ImageView>(R.id.ivTitle)
        val textView = v!!.findViewById<TextView>(R.id.tvTitle)

        imageView.setImageBitmap(item.getBitmap())
        textView.text = item.getTitle()

        return v!!
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(i: Int): Any {
        return list[i]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun addPhoto(item: ImageItem) {

        list.add(item)

        notifyDataSetChanged()
    }
}