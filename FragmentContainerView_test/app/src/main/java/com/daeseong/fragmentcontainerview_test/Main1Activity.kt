package com.daeseong.fragmentcontainerview_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction

class Main1Activity : AppCompatActivity() {

    private lateinit var fcv: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        fcv = findViewById(R.id.fcv)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv, Fragment1())
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}