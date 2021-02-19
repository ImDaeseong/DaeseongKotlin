package com.daeseong.swipe_test


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.swipe_test.SwipeFrameLayout.OnSwipeFrameListener


class Swipe3Activity : AppCompatActivity() {

    private val tag = Swipe3Activity::class.java.simpleName

    private var swipeFrameLayout: SwipeFrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe3)

        swipeFrameLayout = findViewById<SwipeFrameLayout>(R.id.swFL)
        swipeFrameLayout!!.setOnSwipeListener(object : OnSwipeFrameListener {

            override fun swipeLeft() {
                Log.e(tag, "swipeLeft")
            }

            override fun swipeRight() {
                Log.e(tag, "swipeRight")
            }

            override fun swipeUp() {
                Log.e(tag, "swipeUp")
            }

            override fun swipeDown() {
                Log.e(tag, "swipeDown")
            }
        })
    }
}
