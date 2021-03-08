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

    private var Main_drawerLayout: DrawerLayout? = null
    private var Main_picabi: ImageView? = null
    private var Main_picareser:ImageView? = null
    private var Main_picamenu:ImageView? = null
    private var Main_tabLayout: TabLayout? = null
    private var Main_navigationView: NavigationView? = null
    private var Main_viewPager: ViewPager? = null
    private var mainPagerAdapter: MainPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Main_drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        Main_tabLayout = findViewById<TabLayout>(R.id.maintabLayout)

        Main_picabi = findViewById<ImageView>(R.id.picabi)
        Main_picareser = findViewById<ImageView>(R.id.picareser)
        Main_picamenu = findViewById<ImageView>(R.id.picamenu)

        Main_viewPager = findViewById<ViewPager>(R.id.mainviewPager)

        Main_navigationView = findViewById<NavigationView>(R.id.mainnav_view)

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

    override fun onBackPressed() {

        if (Main_drawerLayout!!.isDrawerOpen(GravityCompat.END)) {
            Main_drawerLayout!!.closeDrawer(GravityCompat.END)
        } else {
            super.onBackPressed()
        }
    }

    private fun setInitTabLayout() {

        Main_tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                Log.e(tag, "tab.getPosition():" + tab.position)

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
        Main_viewPager!!.adapter = mainPagerAdapter
        Main_tabLayout!!.setupWithViewPager(Main_viewPager)
    }

}
