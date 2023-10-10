package com.daeseong.bottomnavigationview_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main7Activity : AppCompatActivity() {

    private val tag = Main7Activity::class.java.simpleName

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager
    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            viewPager.currentItem = when (menuItem.itemId) {
                R.id.list -> 0
                R.id.sentence -> 1
                R.id.word -> 2
                R.id.myword -> 3
                R.id.setting -> 4
                else -> 0 // Default to the first item
            }
            false
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                menuItem?.isChecked = false
                menuItem = bottomNavigationView.menu.getItem(position)
                menuItem?.isChecked = true
            }
        })

        setupViewPager(viewPager)
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