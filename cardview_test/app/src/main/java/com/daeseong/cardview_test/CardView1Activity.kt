package com.daeseong.cardview_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CardView1Activity : AppCompatActivity() {

    private val tag: String = CardView1Activity::class.java.simpleName

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private val items: ArrayList<DataItem> = arrayListOf(
        DataItem(R.drawable.banner1, "#1"),
        DataItem(R.drawable.banner2, "#2"),
        DataItem(R.drawable.banner1, "#3"),
        DataItem(R.drawable.banner2, "#4"),
        DataItem(R.drawable.banner1, "#5")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view1)

        recyclerView = findViewById(R.id.recyclerview1)

        layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = RecyclerAdapter(applicationContext, items)
    }
}
