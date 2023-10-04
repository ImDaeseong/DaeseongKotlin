package com.daeseong.tablayout_test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class Main9Activity : AppCompatActivity() {

    private val tag = Main9Activity::class.java.simpleName

    private lateinit var tl1: TabLayout
    private lateinit var v1: View
    private lateinit var v2: View
    private var nPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        v1 = findViewById(R.id.v1)
        v2 = findViewById(R.id.v2)

        tl1 = findViewById(R.id.tl1)
        tl1.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                nPos = tab.position
                initTab(nPos)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        initTab(nPos)
    }

    private fun initTab(index: Int) {
        v1.visibility = if (index == 0) View.VISIBLE else View.GONE
        v2.visibility = if (index == 1) View.VISIBLE else View.GONE

        tl1.getTabAt(index)?.select()
    }
}