package com.daeseong.calendar_test

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.widget.GridView
import kotlin.math.abs


class SwipeGridView : GridView {

    private val SWIPE_THRESHOLD = 30
    private val SWIPE_VELOCITY_THRESHOLD = 1

    private var gestureDetector: GestureDetector? = null
    private var swipeFrameListener: OnSwipeFrameListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {

        gestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {

            override fun onDown(motionEvent: MotionEvent): Boolean {
                return true
            }

            override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float,  velocityY: Float): Boolean {
                var result = false
                val diffX = e2.x - e1.x
                val diffY = e2.y - e1.y
                if (abs(diffX) > abs(diffY)) {
                    if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            if (swipeFrameListener != null) {
                                swipeFrameListener!!.swipeRight()
                            }
                        } else {
                            if (swipeFrameListener != null) {
                                swipeFrameListener!!.swipeLeft()
                            }
                        }
                        result = true
                    }
                } else {
                    if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY < 0) {
                            if (swipeFrameListener != null) {
                                swipeFrameListener!!.swipeUp()
                            }
                        } else {
                            if (swipeFrameListener != null) {
                                swipeFrameListener!!.swipeDown()
                            }
                        }
                        result = true
                    }
                }
                return result
            }
        })
    }

    fun setOnSwipeListener(swipeFrameListener: OnSwipeFrameListener?) {
        this.swipeFrameListener = swipeFrameListener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector!!.onTouchEvent(event)
        return true
    }

    interface OnSwipeFrameListener {
        fun swipeLeft()
        fun swipeRight()
        fun swipeUp()
        fun swipeDown()
    }
}