package com.daeseong.swiperefreshlayout_test

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {

    private val TAG = MainActivity3::class.java.simpleName

    private var swipeRefreshLayoutEx: SwipeRefreshLayoutEx? = null

    private var wait: View? = null
    private var iv1: ImageView? = null
    private var slide_down: Animation? = null
    private var loading:Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        wait = findViewById(R.id.wait)
        iv1 = wait!!.findViewById<View>(R.id.iv1) as ImageView

        slide_down = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        loading = AnimationUtils.loadAnimation(this, R.anim.loading)

        swipeRefreshLayoutEx = findViewById<View>(R.id.swLayout) as SwipeRefreshLayoutEx
        swipeRefreshLayoutEx!!.hideProgressBar(true)
        swipeRefreshLayoutEx!!.setOnRefreshListener {

            swipeRefreshLayoutEx!!.isRefreshing = true

            wait!!.startAnimation(slide_down)
            wait!!.visibility = View.VISIBLE

            iv1!!.startAnimation(loading)

            Handler().postDelayed({

                if (swipeRefreshLayoutEx!!.isRefreshing) {
                    swipeRefreshLayoutEx!!.isRefreshing = false
                }

                requestData()

                wait!!.visibility = View.GONE
                wait!!.clearAnimation()

                iv1!!.clearAnimation()
            }, 1000)
        }
    }

    fun requestData() {
        Log.e(TAG, "업데이트 완료")
    }
}