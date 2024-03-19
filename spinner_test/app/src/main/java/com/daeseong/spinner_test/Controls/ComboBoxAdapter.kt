package com.daeseong.spinner_test.Controls

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.daeseong.spinner_test.R

class ComboBoxAdapter(private val context: Context) : BaseAdapter() {

    private val list: MutableList<String> = ArrayList()

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.combobox_item, null)
        }

        val textView = view!!.findViewById<TextView>(R.id.tv1)
        textView.text = list[position]

        return view
    }

    fun addItem(item: String) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun removeItem(item: String) {
        list.remove(item)
        notifyDataSetChanged()
    }

    fun getList(): List<String> {
        return list
    }
}