package com.daeseong.bottomnavigationview_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragments: List<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        initFragments()

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            Log.e(tag, menuItem.itemId.toString())
            when (menuItem.itemId) {
                R.id.list -> selectIndex(0)
                R.id.sentence -> selectIndex(1)
                R.id.word -> selectIndex(2)
                R.id.myword -> selectIndex(3)
                R.id.setting -> selectIndex(4)
            }
            true
        }

        selectIndex(0)
    }

    private fun initFragments() {
        fragments = listOf(Fragment1(), Fragment2(), Fragment3(), Fragment4(), Fragment5())
    }

    private fun selectIndex(nIndex: Int) {

        val fragment = fragments[nIndex]

        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }

}
