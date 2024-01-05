package com.daeseong.fragmentcontainerview_test

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class Main2Activity : AppCompatActivity() {

    private lateinit var fcv: FrameLayout
    private lateinit var bnv: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        fcv = findViewById(R.id.fcv)
        bnv = findViewById(R.id.bnv)

        loadFragment(Fragment1())

        bnv.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.fragment1 -> {
                        loadFragment(Fragment1())
                        return true
                    }
                    R.id.fragment2 -> {
                        loadFragment(Fragment2())
                        return true
                    }
                }
                return false
            }
        })
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fcv, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}