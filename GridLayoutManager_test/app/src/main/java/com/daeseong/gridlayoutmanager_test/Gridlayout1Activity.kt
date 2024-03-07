package com.daeseong.gridlayoutmanager_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Gridlayout1Activity : AppCompatActivity() {

    private lateinit var rv1: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var v2: View

    private val list = ArrayList<Item>().apply {
        add(Item("image1", R.drawable.a))
        add(Item("image2", R.drawable.a))
        add(Item("image3", R.drawable.a))
        add(Item("image4", R.drawable.a))
        add(Item("image5", R.drawable.a))
        add(Item("image6", R.drawable.a))
        add(Item("image7", R.drawable.a))
        add(Item("image8", R.drawable.a))
        add(Item("image9", R.drawable.a))
        add(Item("image10", R.drawable.a))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gridlayout1)

        rv1 = findViewById(R.id.rv1)
        adapter = RecyclerViewAdapter(applicationContext, list)

        val layoutManager = GridLayoutManager(applicationContext, 2)
        rv1.layoutManager = layoutManager

        // 간격 추가
        val spacingInPixels = 20
        rv1.addItemDecoration(GridSpacingItem(2, spacingInPixels, true))

        rv1.adapter = adapter

        // 동적 추가
        v2 = findViewById(R.id.v2)
        v2.setOnClickListener {
            val index = list.size
            list.add(Item("image$index", R.drawable.a))
            adapter.notifyItemInserted(index)
        }
    }
}