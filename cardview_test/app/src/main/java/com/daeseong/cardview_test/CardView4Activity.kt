package com.daeseong.cardview_test


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CardView4Activity : AppCompatActivity() {

    private val tag: String = CardView4Activity::class.java.simpleName

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private lateinit var itemList: ArrayList<DataItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view4)

        itemList = ArrayList()

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerViewAdapter = RecyclerViewAdapter(itemList)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter

        prepareItems()
    }

    private fun prepareItems() {

        var item = DataItem(R.drawable.banner1, "item1")
        itemList.add(item)

        item = DataItem(R.drawable.banner2, "item2")
        itemList.add(item)

        item = DataItem(R.drawable.banner1, "item3")
        itemList.add(item)

        item = DataItem(R.drawable.banner2, "item4")
        itemList.add(item)

        recyclerViewAdapter!!.notifyDataSetChanged()
    }
}
