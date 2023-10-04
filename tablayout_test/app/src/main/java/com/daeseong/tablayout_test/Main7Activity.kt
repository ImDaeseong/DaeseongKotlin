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

    private lateinit var tabLayout: TabLayout
    private lateinit var dividerLine: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        dividerLine = findViewById(R.id.divider_line)

        tabLayout = findViewById(R.id.tablayout)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.e(tag, "tab.position: ${tab.position}")
                initTab(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        initTab(0)
    }

    private fun initTab(index: Int) {
        val colors = arrayOf(
            R.color.holo_orange_dark,
            R.color.win8_red,
            R.color.DarkGoldenrod,
            R.color.FireBrick,
            R.color.Magenta
        )

        dividerLine.setBackgroundColor(ContextCompat.getColor(this, colors[index]))
        tabLayout.getTabAt(index)?.select()
    }
}