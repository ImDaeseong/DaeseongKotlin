package com.daeseong.cardview_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter(var itemList: ArrayList<DataItem>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item4_cardview, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.title.text = itemList[position].getTitle()
        holder.image.setBackgroundResource(itemList[position].getImage())
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title: TextView = v.findViewById<View>(R.id.title) as TextView
        var image: ImageView = v.findViewById<View>(R.id.image) as ImageView
    }
}