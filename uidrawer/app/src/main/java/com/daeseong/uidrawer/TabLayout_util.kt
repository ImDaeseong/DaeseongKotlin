package com.daeseong.uidrawer

import android.util.Log
import com.google.android.material.tabs.TabLayout

object TabLayout_util {

    private val tag = TabLayout_util::class.java.simpleName

    fun dynamicSetTabLayoutMode(tabLayout: TabLayout) {

        var nTabWidth = 0

        for (i in 0 until tabLayout.childCount) {

            val view = tabLayout.getChildAt(i)
            view.measure(0, 0)
            nTabWidth += view.measuredWidth
        }

        val screenWidth = MyApplication.getScreenWidth()
        if (nTabWidth <= screenWidth) {

            tabLayout.tabGravity = TabLayout.GRAVITY_FILL
            tabLayout.tabMode = TabLayout.MODE_FIXED
            Log.e(tag, "MODE_FIXED")
        } else {

            tabLayout.tabGravity = TabLayout.GRAVITY_CENTER
            tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
            Log.e(tag, "MODE_SCROLLABLE")
        }
    }

}