package com.daeseong.bottomnavigationview_test

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class Main6Activity : AppCompatActivity() {

    private val tag = Main6Activity::class.java.simpleName

    private var viewPager: ViewPager? = null
    private var tab_layout: View? = null
    private var tab_item1: View? = null
    private var tab_item2: View? = null
    private var tab_item3: View? = null
    private var tab_item4: View? = null
    private var tab_item5: View? = null

    private var iv_item1: ImageView? = null
    private var iv_item2: ImageView? = null
    private var iv_item3: ImageView? = null
    private var iv_item4: ImageView? = null
    private var iv_item5: ImageView? = null

    private var tv_item1: TextView? = null
    private var tv_item2: TextView? = null
    private var tv_item3: TextView? = null
    private var tv_item4: TextView? = null
    private var tv_item5: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        init()

        setupViewPager(viewPager)

        selectPage(0)

        tab_item1!!.setOnClickListener { selectPage(0) }

        tab_item2!!.setOnClickListener { selectPage(1) }

        tab_item3!!.setOnClickListener { selectPage(2) }

        tab_item4!!.setOnClickListener { selectPage(3) }

        tab_item5!!.setOnClickListener { selectPage(4) }
    }

    private fun init() {

        viewPager = findViewById(R.id.viewPager)

        tab_layout = findViewById(R.id.tab_layout)

        tab_item1 = tab_layout!!.findViewById(R.id.tab_item1) as View
        iv_item1 = tab_item1!!.findViewById<View>(R.id.iv_item) as ImageView
        tv_item1 = tab_item1!!.findViewById<View>(R.id.tv_item) as TextView

        tab_item2 = tab_layout!!.findViewById(R.id.tab_item2) as View
        iv_item2 = tab_item2!!.findViewById<View>(R.id.iv_item) as ImageView
        tv_item2 = tab_item2!!.findViewById<View>(R.id.tv_item) as TextView

        tab_item3 = tab_layout!!.findViewById(R.id.tab_item3) as View
        iv_item3 = tab_item3!!.findViewById<View>(R.id.iv_item) as ImageView
        tv_item3 = tab_item3!!.findViewById<View>(R.id.tv_item) as TextView

        tab_item4 = tab_layout!!.findViewById(R.id.tab_item4) as View
        iv_item4 = tab_item4!!.findViewById<View>(R.id.iv_item) as ImageView
        tv_item4 = tab_item4!!.findViewById<View>(R.id.tv_item) as TextView

        tab_item5 = tab_layout!!.findViewById(R.id.tab_item5) as View
        iv_item5 = tab_item5!!.findViewById<View>(R.id.iv_item) as ImageView
        tv_item5 = tab_item5!!.findViewById<View>(R.id.tv_item) as TextView

        iv_item1!!.setImageResource(R.drawable.book)
        tv_item1!!.text = "메뉴1"

        iv_item2!!.setImageResource(R.drawable.book)
        tv_item2!!.text = "메뉴2"

        iv_item3!!.setImageResource(R.drawable.book)
        tv_item3!!.text = "메뉴3"

        iv_item4!!.setImageResource(R.drawable.book)
        tv_item4!!.text = "메뉴4"

        iv_item5!!.setImageResource(R.drawable.book)
        tv_item5!!.text = "메뉴5"
    }

    private fun selectPage(index: Int) {

        viewPager!!.currentItem = index
        when (index) {
            0 -> {
                iv_item1!!.setImageResource(R.drawable.bookg)
                iv_item2!!.setImageResource(R.drawable.book)
                iv_item3!!.setImageResource(R.drawable.book)
                iv_item4!!.setImageResource(R.drawable.book)
                iv_item5!!.setImageResource(R.drawable.book)
            }
            1 -> {
                iv_item1!!.setImageResource(R.drawable.book)
                iv_item2!!.setImageResource(R.drawable.bookg)
                iv_item3!!.setImageResource(R.drawable.book)
                iv_item4!!.setImageResource(R.drawable.book)
                iv_item5!!.setImageResource(R.drawable.book)
            }
            2 -> {
                iv_item1!!.setImageResource(R.drawable.book)
                iv_item2!!.setImageResource(R.drawable.book)
                iv_item3!!.setImageResource(R.drawable.bookg)
                iv_item4!!.setImageResource(R.drawable.book)
                iv_item5!!.setImageResource(R.drawable.book)
            }
            3 -> {
                iv_item1!!.setImageResource(R.drawable.book)
                iv_item2!!.setImageResource(R.drawable.book)
                iv_item3!!.setImageResource(R.drawable.book)
                iv_item4!!.setImageResource(R.drawable.bookg)
                iv_item5!!.setImageResource(R.drawable.book)
            }
            4 -> {
                iv_item1!!.setImageResource(R.drawable.book)
                iv_item2!!.setImageResource(R.drawable.book)
                iv_item3!!.setImageResource(R.drawable.book)
                iv_item4!!.setImageResource(R.drawable.book)
                iv_item5!!.setImageResource(R.drawable.bookg)
            }
        }
    }

    private fun setupViewPager(v: ViewPager?) {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        Fragment1().newInstance()?.let { viewPagerAdapter.addFragment(it) }//Fragment1())
        Fragment2().newInstance()?.let { viewPagerAdapter.addFragment(it) }//Fragment2())
        Fragment3().newInstance()?.let { viewPagerAdapter.addFragment(it) }//Fragment3())
        Fragment4().newInstance()?.let { viewPagerAdapter.addFragment(it) }//Fragment4())
        Fragment5().newInstance()?.let { viewPagerAdapter.addFragment(it) }//Fragment5())
        v!!.adapter = viewPagerAdapter
    }
}