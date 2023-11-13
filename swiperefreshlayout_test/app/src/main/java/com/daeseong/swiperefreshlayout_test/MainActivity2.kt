package com.daeseong.swiperefreshlayout_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity2 : AppCompatActivity() {

    private val tag = MainActivity2::class.java.simpleName

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var wait: View
    private lateinit var iv1: ImageView
    private lateinit var slideDown: Animation
    private lateinit var loading: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        wait = findViewById(R.id.wait)
        iv1 = wait.findViewById(R.id.iv1)

        slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        loading = AnimationUtils.loadAnimation(this, R.anim.loading)

        swipeRefreshLayout = findViewById(R.id.swLayout)
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_500)
        swipeRefreshLayout.setProgressViewOffset(true, -10000, -10000)

        swipeRefreshLayout.setOnRefreshListener {

            with(swipeRefreshLayout) {

                isRefreshing = true
                wait.startAnimation(slideDown)
                wait.visibility = View.VISIBLE
                iv1.startAnimation(loading)

                postDelayed({
                    if (isRefreshing) {
                        isRefreshing = false
                    }

                    requestData()

                    wait.visibility = View.GONE
                    wait.clearAnimation()

                    iv1.clearAnimation()
                }, 1000)
            }
        }

    }

    private fun requestData() {
        Log.e(tag, "업데이트 완료")
    }
}
