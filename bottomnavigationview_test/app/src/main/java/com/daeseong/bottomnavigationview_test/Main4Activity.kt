package com.daeseong.bottomnavigationview_test

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView


class Main4Activity : AppCompatActivity() {

    private val tag = Main4Activity::class.java.simpleName

    private var bottomNavigationView: BottomNavigationView? = null
    private var viewPager: ViewPager? = null
    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        viewPager = findViewById(R.id.viewPager)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView!!.setOnNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.list -> viewPager!!.currentItem = 0
                R.id.sentence -> viewPager!!.currentItem = 1
                R.id.word -> viewPager!!.currentItem = 2
                R.id.myword -> viewPager!!.currentItem = 3
                R.id.setting -> viewPager!!.currentItem = 4
            }
            false
        }

        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                if (menuItem != null) {
                    menuItem!!.isChecked = false
                } else {
                    bottomNavigationView!!.menu.getItem(0).isChecked = false
                }
                bottomNavigationView!!.menu.getItem(position).isChecked = true
                menuItem = bottomNavigationView!!.menu.getItem(position)
            }
        })

        setupViewPager(viewPager)
    }

    private fun setupViewPager(v: ViewPager?) {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(Fragment1())
        viewPagerAdapter.addFragment(Fragment2())
        viewPagerAdapter.addFragment(Fragment3())
        viewPagerAdapter.addFragment(Fragment4())
        viewPagerAdapter.addFragment(Fragment5())
        v!!.adapter = viewPagerAdapter
    }
}
