package com.daeseong.tablayout_test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class Main7Activity : AppCompatActivity() {

    private val tag = Main7Activity::class.java.simpleName

    private var tabLayout: TabLayout? = null
    private var divider_line: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        divider_line = findViewById<View>(R.id.divider_line)

        tabLayout = findViewById<TabLayout>(R.id.tablayout)
        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.e(tag, "tab.getPosition():" + tab.position)
                initTab(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        initTab(0)
    }

    private fun initTab(nIndex: Int) {
        when (nIndex) {
            0 -> {
                divider_line!!.setBackgroundColor(ContextCompat.getColor(this, R.color.holo_orange_dark))
            }
            1 -> {
                divider_line!!.setBackgroundColor(ContextCompat.getColor(this, R.color.win8_red))
            }
            2 -> {
                divider_line!!.setBackgroundColor(ContextCompat.getColor(this, R.color.DarkGoldenrod))
            }
            3 -> {
                divider_line!!.setBackgroundColor(ContextCompat.getColor(this, R.color.FireBrick))
            }
            4 -> {
                divider_line!!.setBackgroundColor(ContextCompat.getColor(this, R.color.Magenta))
            }
        }

        tabLayout!!.getTabAt(nIndex)!!.select()
    }

}