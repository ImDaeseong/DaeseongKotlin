package com.daeseong.tablayout_test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private var tabLayout: TabLayout? = null
    private var divider_line: View? = null
    private var tab1: TabItem? = null
    private var tab2: TabItem? = null
    private var tab3: TabItem? = null
    private var tab4: TabItem? = null
    private var tab5: TabItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tabLayout = findViewById<TabLayout>(R.id.tablayout)
        tab1 = findViewById<TabItem>(R.id.tab1)
        tab2 = findViewById<TabItem>(R.id.tab2)
        tab3 = findViewById<TabItem>(R.id.tab3)
        tab4 = findViewById<TabItem>(R.id.tab4)
        tab5 = findViewById<TabItem>(R.id.tab5)

        divider_line = findViewById<View>(R.id.divider_line)

        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.e(tag, "tab.getPosition():" + tab.position)
                initTab(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun initTab(nIndex: Int) {
        when (nIndex) {
            0 -> {
                divider_line!!.setBackgroundColor(ContextCompat.getColor(this, R.color.holo_orange_dark))

                tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.booka)
                tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.booka)
                tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.booka)
                tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.booka)
                tabLayout!!.getTabAt(4)!!.setIcon(R.drawable.booka)
            }
            1 -> {
                divider_line!!.setBackgroundColor(ContextCompat.getColor(this, R.color.win8_red))

                tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.bookb)
                tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.bookb)
                tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.bookb)
                tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.bookb)
                tabLayout!!.getTabAt(4)!!.setIcon(R.drawable.bookb)
            }
            2 -> {
                divider_line!!.setBackgroundColor(ContextCompat.getColor(this, R.color.DarkGoldenrod))

                tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.bookc)
                tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.bookc)
                tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.bookc)
                tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.bookc)
                tabLayout!!.getTabAt(4)!!.setIcon(R.drawable.bookc)
            }
            3 -> {
                divider_line!!.setBackgroundColor(ContextCompat.getColor(this, R.color.FireBrick))

                tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.bookd)
                tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.bookd)
                tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.bookd)
                tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.bookd)
                tabLayout!!.getTabAt(4)!!.setIcon(R.drawable.bookd)
            }
            4 -> {
                divider_line!!.setBackgroundColor(ContextCompat.getColor(this, R.color.Magenta))

                tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.booke)
                tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.booke)
                tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.booke)
                tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.booke)
                tabLayout!!.getTabAt(4)!!.setIcon(R.drawable.booke)
            }
        }
    }

}