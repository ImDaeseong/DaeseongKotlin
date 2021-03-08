package com.daeseong.uidrawer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tag = MainPagerAdapter::class.java.simpleName

    companion object {
        private const val TAB_COUNT = 5
    }

    private val titles = arrayOf("텝1", "텝2", "텝3", "텝4", "텝5")

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MainTab1Fragment()
            1 -> return MainTab2Fragment()
            2 -> return MainTab3Fragment()
            3 -> return MainTab4Fragment()
            4 -> return MainTab5Fragment()
        }
        return null!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getCount(): Int {
        return TAB_COUNT
    }
}