package com.daeseong.bottomnavigationview_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

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
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        setupViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager).apply {
            addFragment(Fragment1())
            addFragment(Fragment2())
            addFragment(Fragment3())
            addFragment(Fragment4())
            addFragment(Fragment5())
        }
        viewPager.adapter = viewPagerAdapter
    }
}
