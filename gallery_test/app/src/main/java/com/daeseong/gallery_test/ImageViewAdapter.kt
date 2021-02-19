package com.daeseong.gallery_test



import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class ImageViewAdapter(context: Context, ResourceID: Int,  arrayList: ArrayList<ImageItem>, bItem: Boolean) : ArrayAdapter<ImageItem>(context, ResourceID, arrayList) {

    private val arrayList : ArrayList<ImageItem> = arrayList
    private val bItem: Boolean = bItem

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var v: View? = null

        if (bItem) {
            v = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.grid_item1, parent, false)
        }

        val imageView = v!!.findViewById<ImageView>(R.id.ivTitle)
        val textView = v!!.findViewById<TextView>(R.id.tvTitle)

        val item = arrayList[position]
        imageView.setImageBitmap(item.getBitmap())
        textView.text = item.getTitle()

        return v
    }

    fun addPhoto(item: ImageItem) {
        arrayList.add(item)
        notifyDataSetChanged()
    }
}