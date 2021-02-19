package com.daeseong.calendar_test

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs


open class SwipeListener(context: Context) :  OnTouchListener {

    companion object {
        private val tag = SwipeListener::class.java.simpleName
        private const val SWIPE_THRESHOLD = 30
        private const val SWIPE_VELOCITY_THRESHOLD = 1
    }

    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    open fun swipeLeft(): Boolean {
        return false
    }

    open fun swipeRight(): Boolean {
        return false
    }

    open fun swipeUp(): Boolean {
        return false
    }

    open fun swipeDown(): Boolean {
        return false
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {

        if(motionEvent == null || gestureDetector == null)
            return false

        return gestureDetector.onTouchEvent(motionEvent)
    }

    private inner class GestureListener : SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            var result = false
            val diffX = e2.x - e1.x
            val diffY = e2.y - e1.y

            if (abs(diffX) > abs(diffY)) {

                if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        swipeRight()
                    } else {
                        swipeLeft()
                    }
                    result = true
                }
            } else {

                if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY < 0) {
                        swipeUp()
                    } else {
                        swipeDown()
                    }
                    result = true
                }
            }
            return result
        }
    }
}