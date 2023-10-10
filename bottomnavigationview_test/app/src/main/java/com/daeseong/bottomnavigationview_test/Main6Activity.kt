package com.daeseong.bottomnavigationview_test

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class Main6Activity : AppCompatActivity() {

    private val tag = Main6Activity::class.java.simpleName

    private lateinit var viewPager: ViewPager
    private lateinit var tab_item1: View
    private lateinit var tab_item2: View
    private lateinit var tab_item3: View
    private lateinit var tab_item4: View
    private lateinit var tab_item5: View

    private lateinit var iv_item1: ImageView
    private lateinit var iv_item2: ImageView
    private lateinit var iv_item3: ImageView
    private lateinit var iv_item4: ImageView
    private lateinit var iv_item5: ImageView

    private lateinit var tv_item1: TextView
    private lateinit var tv_item2: TextView
    private lateinit var tv_item3: TextView
    private lateinit var tv_item4: TextView
    private lateinit var tv_item5: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        initViews()
        setupViewPager(viewPager)
        selectPage(0)

        tab_item1.setOnClickListener { selectPage(0) }
        tab_item2.setOnClickListener { selectPage(1) }
        tab_item3.setOnClickListener { selectPage(2) }
        tab_item4.setOnClickListener { selectPage(3) }
        tab_item5.setOnClickListener { selectPage(4) }
    }

    private fun initViews() {
        viewPager = findViewById(R.id.viewPager)

        tab_item1 = findViewById(R.id.tab_item1)
        iv_item1 = tab_item1.findViewById(R.id.iv_item)
        tv_item1 = tab_item1.findViewById(R.id.tv_item)

        tab_item2 = findViewById(R.id.tab_item2)
        iv_item2 = tab_item2.findViewById(R.id.iv_item)
        tv_item2 = tab_item2.findViewById(R.id.tv_item)

        tab_item3 = findViewById(R.id.tab_item3)
        iv_item3 = tab_item3.findViewById(R.id.iv_item)
        tv_item3 = tab_item3.findViewById(R.id.tv_item)

        tab_item4 = findViewById(R.id.tab_item4)
        iv_item4 = tab_item4.findViewById(R.id.iv_item)
        tv_item4 = tab_item4.findViewById(R.id.tv_item)

        tab_item5 = findViewById(R.id.tab_item5)
        iv_item5 = tab_item5.findViewById(R.id.iv_item)
        tv_item5 = tab_item5.findViewById(R.id.tv_item)

        iv_item1.setImageResource(R.drawable.book)
        tv_item1.text = "메뉴1"

        iv_item2.setImageResource(R.drawable.book)
        tv_item2.text = "메뉴2"

        iv_item3.setImageResource(R.drawable.book)
        tv_item3.text = "메뉴3"

        iv_item4.setImageResource(R.drawable.book)
        tv_item4.text = "메뉴4"

        iv_item5.setImageResource(R.drawable.book)
        tv_item5.text = "메뉴5"
    }

    private fun selectPage(index: Int) {
        viewPager.currentItem = index
        val imageViews = arrayOf(iv_item1, iv_item2, iv_item3, iv_item4, iv_item5)
        for (i in imageViews.indices) {
            imageViews[i].setImageResource(if (i == index) R.drawable.bookg else R.drawable.book)
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager).apply {
            addFragment(Fragment1.newInstance())
            addFragment(Fragment2.newInstance())
            addFragment(Fragment3.newInstance())
            addFragment(Fragment4.newInstance())
            addFragment(Fragment5.newInstance())
        }
        viewPager.adapter = viewPagerAdapter
    }
}