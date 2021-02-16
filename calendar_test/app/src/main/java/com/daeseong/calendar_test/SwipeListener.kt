package com.daeseong.calendar_test

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs


class SwipeListener(context: Context) : SimpleOnGestureListener(), OnTouchListener {

    private val tag = SwipeListener::class.java.simpleName
    private val SWIPE_THRESHOLD = 30
    private val SWIPE_VELOCITY_THRESHOLD = 1

    var gestureDetector: GestureDetector = GestureDetector(context, this)

    private fun swipeLeft(): Boolean {
        return false
    }

    private fun swipeRight(): Boolean {
        return false
    }

    private fun swipeUp(): Boolean {
        return false
    }

    private fun swipeDown(): Boolean {
        return false
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float ): Boolean {
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
        //return super.onFling(e1, e2, velocityX, velocityY);
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(motionEvent)
        return false
    }
}