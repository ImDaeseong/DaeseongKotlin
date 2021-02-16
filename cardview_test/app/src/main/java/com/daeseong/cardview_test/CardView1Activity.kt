package com.daeseong.cardview_test


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CardView1Activity : AppCompatActivity() {

    private val tag: String = CardView1Activity::class.java.simpleName

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var items: ArrayList<DataItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view1)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerview1)

        layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager

        items = ArrayList()
        items.add(DataItem(R.drawable.banner1, "#1"))
        items.add(DataItem(R.drawable.banner2, "#2"))
        items.add(DataItem(R.drawable.banner1, "#3"))
        items.add(DataItem(R.drawable.banner2, "#4"))
        items.add(DataItem(R.drawable.banner1, "#5"))
        recyclerView.adapter = RecyclerAdapter(applicationContext, items)
    }
}
