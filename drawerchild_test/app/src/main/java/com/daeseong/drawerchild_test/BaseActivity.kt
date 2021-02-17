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

    private var Main_drawerLayout: DrawerLayout? = null
    private var Main_navigationView: NavigationView? = null
    private var toggle: ActionBarDrawerToggle? = null
    private var headerView: View? = null
    private var btnheader1: Button? = null
    private var btnheader2: Button? = null

    override fun setContentView(layoutResID: Int) {

        //super.setContentView(layoutResID);

        Main_drawerLayout = layoutInflater.inflate(R.layout.activity_base, null) as DrawerLayout

        val activityContainer = Main_drawerLayout!!.findViewById<View>(R.id.activity_content) as ConstraintLayout
        layoutInflater.inflate(layoutResID, activityContainer, true)

        super.setContentView(Main_drawerLayout)

        Main_navigationView = findViewById<NavigationView>(R.id.mainnav_view)

        headerView = Main_navigationView!!.getHeaderView(0)

        //토글글
        toggle= ActionBarDrawerToggle(this, Main_drawerLayout,null, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        Main_drawerLayout!!.addDrawerListener(toggle!!)
        toggle!!.syncState();

        //메뉴1
        btnheader1 = headerView!!.findViewById<View>(R.id.btnheader1) as Button
        btnheader1!!.setOnClickListener {

            startActivity(Intent(this, Child1Activity::class.java))
            closeSlideMenu()
        }

        //메뉴2
        btnheader2 = headerView!!.findViewById<View>(R.id.btnheader2) as Button
        btnheader2!!.setOnClickListener {

            startActivity(Intent(this, Child2Activity::class.java))
            closeSlideMenu()
        }
    }

    private fun closeSlideMenu() {

        if (Main_drawerLayout!!.isDrawerOpen(GravityCompat.END)) {

            Main_drawerLayout!!.closeDrawer(GravityCompat.END)

        } else {

            Main_drawerLayout!!.openDrawer(GravityCompat.END)
        }

    }
}
