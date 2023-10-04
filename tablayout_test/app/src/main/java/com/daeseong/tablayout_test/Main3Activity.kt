package com.daeseong.tablayout_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName

    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        tabLayout = findViewById(R.id.tablayout)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.e(tag, "tab.position: ${tab.position}")
                initTab(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        initTab(4)
    }

    private fun initTab(index: Int) {
        val drawables = arrayOf(
            R.drawable.booka,
            R.drawable.bookb,
            R.drawable.bookc,
            R.drawable.bookd,
            R.drawable.booke
        )

        for (i in 0 until tabLayout.tabCount) {
            tabLayout.getTabAt(i)?.setIcon(drawables[index])
        }

        tabLayout.getTabAt(index)?.select()
    }
}
