package com.daeseong.paging3_test.fun6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.daeseong.paging3_test.R

class ItemAdapter : PagingDataAdapter<Item, ItemAdapter.ItemViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tv1: TextView = itemView.findViewById(R.id.tv1)
        private val tv2: TextView = itemView.findViewById(R.id.tv2)
        private val tv3: TextView = itemView.findViewById(R.id.tv3)
        private val tv4: TextView = itemView.findViewById(R.id.tv4)

        fun bind(item: Item) {
            tv1.text = item.id.toString()
            tv2.text = item.name
            tv3.text = item.full_name
            tv4.text = item.html_url
        }
    }

    companion object {

        private val tag = ItemAdapter::class.java.name

        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}
