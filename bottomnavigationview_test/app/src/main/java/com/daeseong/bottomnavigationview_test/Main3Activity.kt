package com.daeseong.bottomnavigationview_test

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager
    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)


        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            viewPager.currentItem = when (menuItem.itemId) {
                R.id.list -> 0
                R.id.sentence -> 1
                R.id.word -> 2
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
            addFragment(Fragment1())
            addFragment(Fragment2())
            addFragment(Fragment3())
        }
        viewPager.adapter = viewPagerAdapter
    }
}
