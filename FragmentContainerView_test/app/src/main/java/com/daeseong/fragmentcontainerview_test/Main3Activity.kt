package com.daeseong.fragmentcontainerview_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main3Activity : AppCompatActivity() {

    private lateinit var fcv: FragmentContainerView
    private lateinit var bnv: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        fcv = findViewById(R.id.fcv)
        bnv = findViewById(R.id.bnv)

        bnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment1 -> {
                    loadFragment(Fragment1())
                    true
                }
                R.id.fragment2 -> {
                    loadFragment(Fragment2())
                    true
                }
                else -> false
            }
        }

        loadFragment(Fragment1())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv, fragment)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}