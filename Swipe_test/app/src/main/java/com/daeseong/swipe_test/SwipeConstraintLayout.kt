package com.daeseong.swipe_test

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.abs

class SwipeConstraintLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    companion object {
        private const val SWIPE_THRESHOLD = 30
        private const val SWIPE_VELOCITY_THRESHOLD = 1
    }

    private val gestureDetector: GestureDetector
    private var swipeConstraintListener: OnSwipeConstraintListener? = null

    init {
        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(motionEvent: MotionEvent): Boolean {
                return true
            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

                e1 ?: return false

                val diffX = e2.x - e1.x
                val diffY = e2.y - e1.y

                return when {
                    abs(diffX) > abs(diffY) -> {
                        if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                swipeConstraintListener?.swipeRight()
                            } else {
                                swipeConstraintListener?.swipeLeft()
                            }
                            true
                        } else {
                            false
                        }
                    }
                    abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD -> {
                        if (diffY < 0) {
                            swipeConstraintListener?.swipeUp()
                        } else {
                            swipeConstraintListener?.swipeDown()
                        }
                        true
                    }
                    else -> false
                }
            }
        })
    }

    fun setOnSwipeListener(listener: OnSwipeConstraintListener?) {
        swipeConstraintListener = listener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    interface OnSwipeConstraintListener {
        fun swipeLeft()
        fun swipeRight()
        fun swipeUp()
        fun swipeDown()
    }
}
