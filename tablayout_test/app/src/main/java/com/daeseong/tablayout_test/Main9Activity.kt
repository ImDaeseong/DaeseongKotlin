package com.daeseong.tablayout_test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class Main9Activity : AppCompatActivity() {

    private val tag = Main9Activity::class.java.simpleName

    private var tl1: TabLayout? = null
    private var v1: View? = null
    private var v2: View? = null
    var nPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        v1 = findViewById<View>(R.id.v1)
        v2 = findViewById<View>(R.id.v2)

        tl1 = findViewById<TabLayout>(R.id.tl1)
        tl1!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                nPos = tab.position
                initTab(nPos)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        initTab(nPos)
    }

    private fun initTab(nIndex: Int) {

        if (nIndex == 0) {
            v1!!.visibility = View.VISIBLE
            v2!!.visibility = View.GONE
        } else if (nIndex == 1) {
            v1!!.visibility = View.GONE
            v2!!.visibility = View.VISIBLE
        }
        tl1!!.getTabAt(nIndex)!!.select()
    }
}