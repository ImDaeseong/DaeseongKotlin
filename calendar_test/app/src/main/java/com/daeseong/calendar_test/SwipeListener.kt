package com.daeseong.calendar_test

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

open class SwipeListener(context: Context) : View.OnTouchListener {

    companion object {
        private const val SWIPE_THRESHOLD = 30 // 스와이프 인식 최소 거리
        private const val SWIPE_VELOCITY_THRESHOLD = 1 // 스와이프 인식 최소 속도
    }

    private val gestureDetector = GestureDetector(context, GestureListener())

    open fun swipeLeft(): Boolean {
        // 왼쪽 스와이프
        return false
    }

    open fun swipeRight(): Boolean {
        // 오른쪽 스와이프
        return false
    }

    open fun swipeUp(): Boolean {
        // 위 스와이프
        return false
    }

    open fun swipeDown(): Boolean {
        // 아래 스와이프
        return false
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        // GestureDetector를 사용하여 터치 이벤트를 처리
        return gestureDetector.onTouchEvent(motionEvent)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            // 터치 이벤트를 계속 수신하려면 true를 반환
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {

            // e1이 null인 경우 false를 반환하여 제스처 인식을 중지
            if (e1 == null) return false

            val diffX = e2.x - e1.x
            val diffY = e2.y - e1.y

            return when {
                abs(diffX) > abs(diffY) && abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD -> {
                    if (diffX > 0) {
                        swipeRight()
                    } else {
                        swipeLeft()
                    }
                }
                abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD -> {
                    if (diffY > 0) {
                        swipeDown()
                    } else {
                        swipeUp()
                    }
                }
                else -> false
            }
        }
    }
}
