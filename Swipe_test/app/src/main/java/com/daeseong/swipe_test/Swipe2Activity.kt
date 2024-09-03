package com.daeseong.swipe_test

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class Swipe2Activity : AppCompatActivity() {

    private val tag = Swipe2Activity::class.java.simpleName

    private lateinit var gestureDetector: GestureDetector

    private val SWIPE_THRESHOLD = 30
    private val SWIPE_VELOCITY_THRESHOLD = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe2)

        // GestureDetector 초기화
        gestureDetector = GestureDetector(this, MyGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // GestureDetector의 onTouchEvent 호출
        return gestureDetector.onTouchEvent(event)
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean = true

        override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            // Null 체크
            if (e1 == null) {
                return false
            }

            val diffX = e2.x - e1.x
            val diffY = e2.y - e1.y

            return when {
                abs(diffX) > abs(diffY) -> {
                    // 수평 스와이프
                    if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            Log.d(tag, "onSwipeRight")
                        } else {
                            Log.d(tag, "onSwipeLeft")
                        }
                        true
                    } else {
                        false
                    }
                }
                abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD -> {
                    // 수직 스와이프
                    if (diffY < 0) {
                        Log.d(tag, "onSwipeUp")
                    } else {
                        Log.d(tag, "onSwipeDown")
                    }
                    true
                }
                else -> false
            }
        }
    }
}
