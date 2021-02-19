package com.daeseong.swipe_test


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.swipe_test.AnimatorUtil.Animato10
import com.daeseong.swipe_test.AnimatorUtil.Animato11
import com.daeseong.swipe_test.AnimatorUtil.Animato8
import com.daeseong.swipe_test.SwipeConstraintLayout.OnSwipeConstraintListener


class Swipe4Activity : AppCompatActivity() {

    private val tag = Swipe4Activity::class.java.simpleName

    private var swipeConstraintLayout: SwipeConstraintLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe4)

        swipeConstraintLayout = findViewById<SwipeConstraintLayout>(R.id.swCL)
        swipeConstraintLayout!!.setOnSwipeListener(object : OnSwipeConstraintListener {

            override fun swipeLeft() {
                Animato10(swipeConstraintLayout!!)
            }

            override fun swipeRight() {
                Animato11(swipeConstraintLayout!!)
            }

            override fun swipeUp() {
                Log.e(tag, "swipeUp")
            }

            override fun swipeDown() {
                Log.e(tag, "swipeDown")
            }
        })

        AnimatoTopBottom()
    }

    private fun AnimatoTopBottom() {

        Log.e(tag, "swipeTop-Bottom")
        Animato8(swipeConstraintLayout!!)
    }

}
