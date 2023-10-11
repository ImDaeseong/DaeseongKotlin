package com.daeseong.uidrawer

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var mainDrawerLayout: DrawerLayout
    private lateinit var mainPicAbi: ImageView
    private lateinit var mainPicAreser: ImageView
    private lateinit var mainPicAmenu: ImageView
    private lateinit var mainTabLayout: TabLayout
    private lateinit var mainNavigationView: NavigationView
    private lateinit var mainViewPager: ViewPager
    private var mainPagerAdapter: MainPagerAdapter? = null

    private var profile: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStatusBarColor()

        mainDrawerLayout = findViewById(R.id.main_drawerLayout)
        mainTabLayout = findViewById(R.id.maintabLayout)
        mainPicAbi = findViewById(R.id.picabi)
        mainPicAreser = findViewById(R.id.picareser)
        mainPicAmenu = findViewById(R.id.picamenu)
        mainViewPager = findViewById(R.id.mainviewPager)
        mainNavigationView = findViewById(R.id.mainnav_view)

        //Drawer Header Menu 접근
        val headerLayout = mainNavigationView.getHeaderView(0)
        profile = headerLayout.findViewById(R.id.imageView1) as ImageView
        profile!!.setImageResource(R.drawable.profile1)
        profile!!.setOnClickListener {
            Log.e(tag, "profile")
        }

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

    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.rgb(0, 0, 0)
        }
    }

    private fun setInitTabLayout() {
        mainTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.e(tag, "tab.getPosition():${tab.position}")
                when (tab.position) {
                    0 -> {
                    }
                    1 -> {
                    }
                    2 -> {
                    }
                    3 -> {
                    }
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
        TabLayout_util.dynamicSetTabLayoutMode(mainTabLayout)
        mainViewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
