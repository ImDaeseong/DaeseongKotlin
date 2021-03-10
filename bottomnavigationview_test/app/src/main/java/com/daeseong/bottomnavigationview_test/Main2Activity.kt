package com.daeseong.bottomnavigationview_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView


class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private var bottomNavigationView: BottomNavigationView? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

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

                when (position) {
                    0 -> bottomNavigationView!!.menu.findItem(R.id.list).isChecked = true
                    1 -> bottomNavigationView!!.menu.findItem(R.id.sentence).isChecked = true
                    2 -> bottomNavigationView!!.menu.findItem(R.id.word).isChecked = true
                    3 -> bottomNavigationView!!.menu.findItem(R.id.myword).isChecked = true
                    4 -> bottomNavigationView!!.menu.findItem(R.id.setting).isChecked = true
                }
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
