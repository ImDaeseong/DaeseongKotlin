package com.daeseong.tablayout_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        tabLayout = findViewById<TabLayout>(R.id.tablayout)
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
                tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.booka)
                tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.booka)
                tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.booka)
                tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.booka)
                tabLayout!!.getTabAt(4)!!.setIcon(R.drawable.booka)
            }
            1 -> {
                tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.bookb)
                tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.bookb)
                tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.bookb)
                tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.bookb)
                tabLayout!!.getTabAt(4)!!.setIcon(R.drawable.bookb)
            }
            2 -> {
                tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.bookc)
                tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.bookc)
                tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.bookc)
                tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.bookc)
                tabLayout!!.getTabAt(4)!!.setIcon(R.drawable.bookc)
            }
            3 -> {
                tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.bookd)
                tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.bookd)
                tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.bookd)
                tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.bookd)
                tabLayout!!.getTabAt(4)!!.setIcon(R.drawable.bookd)
            }
            4 -> {
                tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.booke)
                tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.booke)
                tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.booke)
                tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.booke)
                tabLayout!!.getTabAt(4)!!.setIcon(R.drawable.booke)
            }
        }
    }


}
