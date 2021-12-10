package com.daeseong.swiperefreshlayout_test

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity2 : AppCompatActivity() {

    private val TAG = MainActivity2::class.java.simpleName

    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    private var wait: View? = null
    private var iv1: ImageView? = null
    private var slide_down: Animation? = null
    private var loading:Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        wait = findViewById(R.id.wait)
        iv1 = wait!!.findViewById<ImageView>(R.id.iv1)

        slide_down = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        loading = AnimationUtils.loadAnimation(this, R.anim.loading)


        swipeRefreshLayout = findViewById<View>(R.id.swLayout) as SwipeRefreshLayout
        swipeRefreshLayout!!.setColorSchemeResources(R.color.purple_500)

        //스와이프 민감도 설정(기본:120)
        //swipeRefreshLayout!!.setDistanceToTriggerSync(20)

        //swipeRefreshLayout!!.setSize(SwipeRefreshLayout.DEFAULT)

        //스와이프 프로그래스바 위치 - 숨김
        swipeRefreshLayout!!.setProgressViewOffset(true, -10000, -10000)

        swipeRefreshLayout!!.setOnRefreshListener {

            swipeRefreshLayout!!.isRefreshing = true

            wait!!.startAnimation(slide_down)
            wait!!.visibility = View.VISIBLE

            iv1!!.startAnimation(loading)

            Handler().postDelayed({

                if (swipeRefreshLayout!!.isRefreshing) {
                    swipeRefreshLayout!!.isRefreshing = false
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