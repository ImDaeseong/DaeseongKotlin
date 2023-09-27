package com.daeseong.cardview_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CardView4Activity : AppCompatActivity() {

    private val tag: String = CardView4Activity::class.java.simpleName

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val itemList: ArrayList<DataItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view4)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerViewAdapter = RecyclerViewAdapter(itemList)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter

        prepareItems()
    }

    private fun prepareItems() {

        itemList.apply {
            add(DataItem(R.drawable.banner1, "item1"))
            add(DataItem(R.drawable.banner2, "item2"))
            add(DataItem(R.drawable.banner1, "item3"))
            add(DataItem(R.drawable.banner2, "item4"))
        }

        recyclerViewAdapter.notifyDataSetChanged()
    }
}
