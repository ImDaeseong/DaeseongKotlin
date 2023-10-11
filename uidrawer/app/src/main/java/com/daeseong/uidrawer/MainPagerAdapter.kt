package com.daeseong.uidrawer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabTitles = arrayOf("텝1", "텝2", "텝3", "텝4", "텝5")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MainTab1Fragment()
            1 -> MainTab2Fragment()
            2 -> MainTab3Fragment()
            3 -> MainTab4Fragment()
            4 -> MainTab5Fragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    override fun getCount(): Int {
        return tabTitles.size
    }
}