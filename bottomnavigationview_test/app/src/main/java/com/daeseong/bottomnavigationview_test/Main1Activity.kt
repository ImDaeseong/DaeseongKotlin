package com.daeseong.bottomnavigationview_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var bottomNavigationView: BottomNavigationView? = null
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null

    private var fragment1: Fragment1? = null
    private var fragment2: Fragment2? = null
    private var fragment3: Fragment3? = null
    private var fragment4: Fragment4? = null
    private var fragment5: Fragment5? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        initFragment()

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView!!.setOnNavigationItemSelectedListener { menuItem ->

            Log.e(tag, menuItem.itemId.toString())

            when (menuItem.itemId) {
                R.id.list -> SelectIndex(0)
                R.id.sentence -> SelectIndex(1)
                R.id.word -> SelectIndex(2)
                R.id.myword -> SelectIndex(3)
                R.id.setting -> SelectIndex(4)
            }
            true
        }

        SelectIndex(0)
    }

    private fun initFragment() {
        fragment1 = Fragment1()
        fragment2 = Fragment2()
        fragment3 = Fragment3()
        fragment4 = Fragment4()
        fragment5 = Fragment5()
    }

    private fun SelectIndex(nIndex: Int) {

        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()

        when (nIndex) {
            0 -> {
                fragmentTransaction!!.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                fragmentTransaction!!.replace(R.id.frameLayout, fragment1!!)
                fragmentTransaction!!.commit()
            }
            1 -> {
                fragmentTransaction!!.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                fragmentTransaction!!.replace(R.id.frameLayout, fragment2!!)
                fragmentTransaction!!.commit()
            }
            2 -> {
                fragmentTransaction!!.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                fragmentTransaction!!.replace(R.id.frameLayout, fragment3!!)
                fragmentTransaction!!.commit()
            }
            3 -> {
                fragmentTransaction!!.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                fragmentTransaction!!.replace(R.id.frameLayout, fragment4!!)
                fragmentTransaction!!.commit()
            }
            4 -> {
                fragmentTransaction!!.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                fragmentTransaction!!.replace(R.id.frameLayout, fragment5!!)
                fragmentTransaction!!.commit()
            }
        }
    }

}
