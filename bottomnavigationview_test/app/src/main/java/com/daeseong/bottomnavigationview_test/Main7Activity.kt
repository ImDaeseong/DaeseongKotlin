package com.daeseong.bottomnavigationview_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main7Activity : AppCompatActivity() {

    private val tag = Main7Activity::class.java.simpleName

    private var bottomNavigationView: BottomNavigationView? = null
    private var viewPager: ViewPager? = null
    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

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

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

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
        Fragment1().newInstance()?.let { viewPagerAdapter.addFragment(it) }//Fragment1())
        Fragment2().newInstance()?.let { viewPagerAdapter.addFragment(it) }//Fragment2())
        Fragment3().newInstance()?.let { viewPagerAdapter.addFragment(it) }//Fragment3())
        Fragment4().newInstance()?.let { viewPagerAdapter.addFragment(it) }//Fragment4())
        Fragment5().newInstance()?.let { viewPagerAdapter.addFragment(it) }//Fragment5())
        v!!.adapter = viewPagerAdapter
    }
}