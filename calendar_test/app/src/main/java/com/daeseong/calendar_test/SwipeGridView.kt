package com.daeseong.calendar_test

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.GridView
import kotlin.math.abs

class SwipeGridView : GridView {

    companion object {
        private const val SWIPE_THRESHOLD = 30
        private const val SWIPE_VELOCITY_THRESHOLD = 1
    }

    private lateinit var gestureDetector: GestureDetector
    private var swipeFrameListener: OnSwipeFrameListener? = null

    constructor(context: Context) : super(context) {
        initGesture(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initGesture(context)
    }

    private fun initGesture(context: Context) {
        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(motionEvent: MotionEvent): Boolean {
                // 터치 이벤트를 계속 수신하려면 true 반환
                return true
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                // e1 또는 e2가 null인 경우 false 반환하여 제스처 인식을 중지
                if (e1 == null || e2 == null) return false

                val diffX = e2.x - e1.x
                val diffY = e2.y - e1.y

                return if (abs(diffX) > abs(diffY)) {
                    handleHorizontalSwipe(diffX, velocityX)
                } else {
                    handleVerticalSwipe(diffY, velocityY)
                }
            }

            private fun handleHorizontalSwipe(diffX: Float, velocityX: Float): Boolean {
                return if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        swipeFrameListener?.swipeRight()
                    } else {
                        swipeFrameListener?.swipeLeft()
                    }
                    true
                } else {
                    false
                }
            }

            private fun handleVerticalSwipe(diffY: Float, velocityY: Float): Boolean {
                return if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        swipeFrameListener?.swipeDown()
                    } else {
                        swipeFrameListener?.swipeUp()
                    }
                    true
                } else {
                    false
                }
            }
        })
    }

    fun setOnSwipeListener(swipeFrameListener: OnSwipeFrameListener?) {
        this.swipeFrameListener = swipeFrameListener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 제스처를 감지하지 못하면 super.onTouchEvent(event)를 호출
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    interface OnSwipeFrameListener {
        fun swipeLeft()
        fun swipeRight()
        fun swipeUp()
        fun swipeDown()
    }
}
