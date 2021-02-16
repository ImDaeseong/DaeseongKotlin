package com.daeseong.cardview_test


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(var context: Context, var items: ArrayList<DataItem>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {

        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item1_cardview, null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {

        val item = items[position]
        val drawable = ContextCompat.getDrawable(context, item.getImage())

        holder.image.background = drawable
        holder.title.text = item.getTitle()
        holder.cardView.setOnClickListener {
            Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        var image: ImageView = v.findViewById<View>(R.id.image) as ImageView
        var title: TextView = v.findViewById<View>(R.id.title) as TextView
        var cardView: CardView = v.findViewById<View>(R.id.cardview) as CardView
    }
}