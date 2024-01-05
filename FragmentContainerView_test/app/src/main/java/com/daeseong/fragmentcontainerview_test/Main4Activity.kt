package com.daeseong.fragmentcontainerview_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main4Activity : AppCompatActivity() {

    private lateinit var ncl: NavController
    private lateinit var bnv: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        ncl = (supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment).navController
        bnv = findViewById(R.id.bnv)
        NavigationUI.setupWithNavController(bnv, ncl)
    }
}