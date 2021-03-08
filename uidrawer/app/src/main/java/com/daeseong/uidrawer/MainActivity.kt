package com.daeseong.uidrawer


import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
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

    private var Main_drawerLayout: DrawerLayout? = null
    private var Main_picabi: ImageView? = null
    private var Main_picareser:ImageView? = null
    private var Main_picamenu:ImageView? = null
    private var Main_tabLayout: TabLayout? = null
    private var Main_navigationView: NavigationView? = null
    private var Main_viewPager: ViewPager? = null
    private var mainPagerAdapter: MainPagerAdapter? = null

    private var profile: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.rgb(0, 0, 0)
        }

        Main_drawerLayout = findViewById<DrawerLayout>(R.id.main_drawerLayout)
        Main_tabLayout = findViewById<TabLayout>(R.id.maintabLayout)

        Main_picabi = findViewById<ImageView>(R.id.picabi)
        Main_picareser = findViewById<ImageView>(R.id.picareser)
        Main_picamenu = findViewById<ImageView>(R.id.picamenu)

        Main_viewPager = findViewById<ViewPager>(R.id.mainviewPager)

        Main_navigationView = findViewById<NavigationView>(R.id.mainnav_view)

        //Drawer Header Menu 접근
        val headerLayout = Main_navigationView!!.getHeaderView(0)
        profile = headerLayout.findViewById<View>(R.id.imageView1) as ImageView
        profile!!.setImageResource(R.drawable.profile1)
        profile!!.setOnClickListener {

            Log.e(tag, "profile")
        }

        val toggle = ActionBarDrawerToggle(this, Main_drawerLayout,null, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        Main_drawerLayout!!.setDrawerListener(toggle)
        toggle.syncState()

        Main_navigationView!!.setNavigationItemSelectedListener { item ->

            val id: Int = item.itemId
            Main_drawerLayout!!.closeDrawer(GravityCompat.END)
            true
        }

        Main_picabi!!.setOnClickListener {

            Log.e(tag, "picabi")
        }

        Main_picareser!!.setOnClickListener {

            Log.e(tag, "picareser")
        }

        Main_picamenu!!.setOnClickListener {

            Log.e(tag, "Main_picamenu")

            try {

                if (Main_drawerLayout!!.isDrawerOpen(GravityCompat.END)) {
                    Main_drawerLayout!!.closeDrawer(GravityCompat.END)
                } else {
                    Main_drawerLayout!!.openDrawer(GravityCompat.END)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        setViewPager()
        setInitTabLayout()
    }

    var tabLayoutWidth = Runnable {

        if (Main_tabLayout!!.width < resources.displayMetrics.widthPixels) {

            Log.e(tag, "tabLayoutWidth:MODE_FIXED")

            Main_tabLayout!!.tabMode = TabLayout.MODE_FIXED
            val mParams = Main_tabLayout!!.layoutParams
            mParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            Main_tabLayout!!.layoutParams = mParams

        } else {

            Log.e(tag, "tabLayoutWidth:MODE_SCROLLABLE")

            Main_tabLayout!!.tabMode = TabLayout.MODE_SCROLLABLE
        }
    }

    private fun setInitTabLayout() {

        //Main_tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        //Main_tabLayout!!.tabMode = TabLayout.MODE_FIXED
        //Main_tabLayout!!.setPaddingRelative(0, 0, 0, 0)

        Main_tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                Log.e(tag, "tab.getPosition():" + tab.position)

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
        Main_viewPager!!.adapter = mainPagerAdapter
        Main_tabLayout!!.setupWithViewPager(Main_viewPager)

        //탭 넓이 계산
        //Main_tabLayout!!.post(tabLayoutWidth)
        TabLayout_util.dynamicSetTabLayoutMode(Main_tabLayout!!)
        Main_viewPager!!.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
