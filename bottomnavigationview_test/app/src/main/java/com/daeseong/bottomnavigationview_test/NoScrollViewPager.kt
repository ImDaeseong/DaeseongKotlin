package com.daeseong.bottomnavigationview_test

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NoScrollViewPager : ViewPager {

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

    override fun onInterceptHoverEvent(event: MotionEvent): Boolean {
        return false
        //return super.onInterceptHoverEvent(event);
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return false
        //return super.onTouchEvent(ev);
    }
}
