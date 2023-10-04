package com.daeseong.swipe_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.swipe_test.AnimatorUtil.animate10
import com.daeseong.swipe_test.AnimatorUtil.animate11
import com.daeseong.swipe_test.AnimatorUtil.animate8
import com.daeseong.swipe_test.SwipeConstraintLayout.OnSwipeConstraintListener

class Swipe4Activity : AppCompatActivity() {

    private val tag = Swipe4Activity::class.java.simpleName

    private var swipeConstraintLayout: SwipeConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe4)

        swipeConstraintLayout = findViewById(R.id.swCL)
        swipeConstraintLayout?.setOnSwipeListener(object : OnSwipeConstraintListener {

            override fun swipeLeft() {
                animate10(swipeConstraintLayout!!)
            }

            override fun swipeRight() {
                animate11(swipeConstraintLayout!!)
            }

            override fun swipeUp() {
                Log.e(tag, "swipeUp")
            }

            override fun swipeDown() {
                Log.e(tag, "swipeDown")
            }
        })

        animatoTopBottom()
    }

    private fun animatoTopBottom() {
        Log.e(tag, "swipeTop-Bottom")
        animate8(swipeConstraintLayout!!)
    }
}
