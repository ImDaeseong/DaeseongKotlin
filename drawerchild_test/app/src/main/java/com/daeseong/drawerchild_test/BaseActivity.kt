package com.daeseong.drawerchild_test

import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

open class BaseActivity : AppCompatActivity() {

    private val tag: String = BaseActivity::class.java.simpleName

    private lateinit var mainDrawerLayout: DrawerLayout
    private lateinit var mainNavigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var headerView: View
    private lateinit var btnHeader1: Button
    private lateinit var btnHeader2: Button

    override fun setContentView(layoutResID: Int) {
        mainDrawerLayout = layoutInflater.inflate(R.layout.activity_base, null) as DrawerLayout

        val activityContainer = mainDrawerLayout.findViewById<View>(R.id.activity_content) as ConstraintLayout
        layoutInflater.inflate(layoutResID, activityContainer, true)

        super.setContentView(mainDrawerLayout)

        mainNavigationView = findViewById(R.id.mainnav_view)

        headerView = mainNavigationView.getHeaderView(0)

        // Toggle
        toggle = ActionBarDrawerToggle(this, mainDrawerLayout, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mainDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Menu 1
        btnHeader1 = headerView.findViewById(R.id.btnheader1)
        btnHeader1.setOnClickListener {
            startActivity(Intent(this, Child1Activity::class.java))
            closeSlideMenu()
        }

        // Menu 2
        btnHeader2 = headerView.findViewById(R.id.btnheader2)
        btnHeader2.setOnClickListener {
            startActivity(Intent(this, Child2Activity::class.java))
            closeSlideMenu()
        }
    }

    private fun closeSlideMenu() {
        if (mainDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mainDrawerLayout.closeDrawer(GravityCompat.END)
        } else {
            mainDrawerLayout.openDrawer(GravityCompat.END)
        }
    }
}
