package com.daeseong.uidrawer

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class Main2Activity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var mainDrawerLayout: DrawerLayout
    private lateinit var mainPicAbi: ImageView
    private lateinit var mainPicAreser: ImageView
    private lateinit var mainPicAmenu: ImageView
    private lateinit var mainTabLayout: TabLayout
    private lateinit var mainNavigationView: NavigationView
    private lateinit var mainViewPager: ViewPager
    private var mainPagerAdapter: MainPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mainDrawerLayout = findViewById(R.id.drawer_layout)
        mainTabLayout = findViewById(R.id.maintabLayout)
        mainPicAbi = findViewById(R.id.picabi)
        mainPicAreser = findViewById(R.id.picareser)
        mainPicAmenu = findViewById(R.id.picamenu)
        mainViewPager = findViewById(R.id.mainviewPager)
        mainNavigationView = findViewById(R.id.mainnav_view)


        val toggle = ActionBarDrawerToggle(this, mainDrawerLayout, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mainDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mainNavigationView.setNavigationItemSelectedListener { item ->
            val id: Int = item.itemId
            mainDrawerLayout.closeDrawer(GravityCompat.END)
            true
        }

        mainPicAbi.setOnClickListener {
            Log.e(tag, "picabi")
        }

        mainPicAreser.setOnClickListener {
            Log.e(tag, "picareser")
        }

        mainPicAmenu.setOnClickListener {
            try {
                if (mainDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                    mainDrawerLayout.closeDrawer(GravityCompat.END)
                } else {
                    mainDrawerLayout.openDrawer(GravityCompat.END)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        setViewPager()
        setInitTabLayout()
    }

    override fun onBackPressed() {

        if (mainDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mainDrawerLayout.closeDrawer(GravityCompat.END)
        } else {
            super.onBackPressed()
        }
    }

    private fun setInitTabLayout() {

        mainTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.e(tag, "tab.getPosition():${tab.position}")
                when (tab.position) {
                    0 -> { }
                    1 -> { }
                    2 -> { }
                    3 -> { }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setViewPager() {

        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        mainViewPager.adapter = mainPagerAdapter
        mainTabLayout.setupWithViewPager(mainViewPager)
    }

}
