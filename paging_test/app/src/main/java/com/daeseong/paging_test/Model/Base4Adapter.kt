package com.daeseong.paging_test.Model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.daeseong.paging_test.API.SearchApi.itemData
import com.daeseong.paging_test.R

class Base4Adapter : BaseAdapter() {

    private val tag = Base4Adapter::class.java.simpleName

    private val list: MutableList<itemData> = ArrayList()
    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var tv3: TextView? = null

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(i: Int): itemData {

        Log.e(tag, list[i].toString())
        return list[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {

        val context: Context = viewGroup!!.context
        val myview: View = LayoutInflater.from(context).inflate(R.layout.list_item, null)

        tv1 = myview.findViewById<TextView>(R.id.tv1)
        tv2 = myview.findViewById<TextView>(R.id.tv2)
        tv3 = myview.findViewById<TextView>(R.id.tv3)

        val item = getItem(i)
        tv1!!.text = item.ID
        tv2!!.text = item.NAME
        tv3!!.text = item.HTMLURL

        tv1!!.setOnClickListener {

            Log.e(tag, item.ID!!)
        }

        tv2!!.setOnClickListener {

            Log.e(tag, item.NAME!!)
        }

        tv3!!.setOnClickListener {

            Log.e(tag, item.HTMLURL!!)
        }

        return myview
    }

    fun addAll(list: List<itemData>?) {

        this.list.addAll(list!!)
        notifyDataSetChanged()
    }

    fun add(list: List<itemData>?) {

        this.list.clear()
        this.list.addAll(list!!)
        notifyDataSetChanged()
    }
}
