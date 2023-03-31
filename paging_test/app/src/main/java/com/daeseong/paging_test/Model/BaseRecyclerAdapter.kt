package com.daeseong.paging_test.Model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daeseong.paging_test.API.SearchApi
import com.daeseong.paging_test.R

class BaseRecyclerAdapter(context: Context, var list: ArrayList<SearchApi.itemData> = ArrayList()) : RecyclerView.Adapter<BaseRecyclerAdapter.MyViewHolder>() {

    companion object {
        private const val tag = "BaseRecyclerAdapter"
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {
            val item = list[position]
            holder.tv1.text = item.ID
            holder.tv2.text = item.NAME
            holder.tv3.text = item.HTMLURL
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    override fun getItemCount(): Int = list.size

    fun getItem(position: Int): SearchApi.itemData = list[position]

    fun remove(item: SearchApi.itemData) {
        val position = list.indexOf(item)
        if (position > -1) {
            list.remove(item)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    private fun add(item: SearchApi.itemData) {
        list.add(item)
        notifyItemInserted(list.size)
    }

    fun addAll(list: List<SearchApi.itemData>) {
        for (item in list) {
            add(item)
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tv1: TextView = view.findViewById(R.id.tv1)
        val tv2: TextView = view.findViewById(R.id.tv2)
        val tv3: TextView = view.findViewById(R.id.tv3)

        init {
            view.setOnClickListener {
                Log.e(tag, "MyViewHolder click")
            }
        }
    }
}
