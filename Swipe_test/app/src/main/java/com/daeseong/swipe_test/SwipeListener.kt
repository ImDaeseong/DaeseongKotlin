package com.daeseong.swipe_test

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs

open class SwipeListener(context: Context) : OnTouchListener {

    companion object {
        private const val SWIPE_THRESHOLD = 30
        private const val SWIPE_VELOCITY_THRESHOLD = 1
    }

    private val gestureDetector: GestureDetector = GestureDetector(context, GestureListener())

    open fun swipeLeft(): Boolean = false

    open fun swipeRight(): Boolean = false

    open fun swipeUp(): Boolean = false

    open fun swipeDown(): Boolean = false

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(motionEvent)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean = true

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            var result = false
            val diffX = e2.x - e1.x
            val diffY = e2.y - e1.y

            if (abs(diffX) > abs(diffY)) {
                if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    result = if (diffX > 0) swipeRight() else swipeLeft()
                }
            } else {
                if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    result = if (diffY < 0) swipeUp() else swipeDown()
                }
            }
            return result
        }
    }

}
