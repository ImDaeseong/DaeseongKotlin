package com.daeseong.swipe_test

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlin.math.abs

class Swipe2Activity : AppCompatActivity() {

    private val tag = Swipe2Activity::class.java.simpleName

    private var gestureDetectorCompat: GestureDetectorCompat? = null

    private val SWIPE_THRESHOLD = 30
    private val SWIPE_VELOCITY_THRESHOLD = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe2)

        gestureDetectorCompat = GestureDetectorCompat(this, MyGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetectorCompat?.onTouchEvent(event) ?: false
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean = true

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            val diffX = e2?.x!! - e1?.x!!
            val diffY = e2.y - e1.y

            return if (abs(diffX) > abs(diffY)) {

                if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        Log.e(tag, "onSwipeRight")
                    } else {
                        Log.e(tag, "onSwipeLeft")
                    }
                    true
                } else {
                    false
                }

            } else {

                if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY < 0) {
                        Log.e(tag, "onSwipeUp")
                    } else {
                        Log.e(tag, "onSwipeDown")
                    }
                    true
                } else {
                    false
                }
            }

        }
    }
}
