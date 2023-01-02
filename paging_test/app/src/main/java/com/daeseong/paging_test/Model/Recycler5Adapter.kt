package com.daeseong.paging_test.Model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daeseong.paging_test.API.SearchApi.itemData
import com.daeseong.paging_test.R

class Recycler5Adapter : RecyclerView.Adapter<Recycler5Adapter.MyViewHolder> {

    private val tag = Recycler5Adapter::class.java.simpleName

    private var list: MutableList<itemData> = ArrayList()
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = list[position]

        holder.tv1.text = item.ID
        holder.tv2.text = item.NAME
        holder.tv3.text = item.HTMLURL

        holder.tv1.setOnClickListener {

            Log.e(tag, holder.tv1.text.toString())
        }

        holder.tv2.setOnClickListener {

            Log.e(tag, holder.tv2.text.toString())
        }

        holder.tv3.setOnClickListener {

            Log.e(tag, holder.tv3.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return if (list == null) {
            0
        } else list.size
    }

    fun addAll(list: List<itemData>?) {

        if (this.list == null) {
            this.list = java.util.ArrayList()
        }

        this.list.addAll(list!!)
        notifyDataSetChanged()
    }

    fun add(list: List<itemData>?) {

        if (this.list == null) {
            this.list = java.util.ArrayList()
        }

        this.list.clear()
        this.list.addAll(list!!)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tv1: TextView
        val tv2: TextView
        val tv3: TextView

        init {
            tv1 = view.findViewById<View>(R.id.tv1) as TextView
            tv2 = view.findViewById<View>(R.id.tv2) as TextView
            tv3 = view.findViewById<View>(R.id.tv3) as TextView
        }
    }
}