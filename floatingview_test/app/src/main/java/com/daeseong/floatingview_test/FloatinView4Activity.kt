package com.daeseong.floatingview_test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewTreeObserver
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.MATCH_CONSTRAINT

class FloatinView4Activity : AppCompatActivity() {

    private lateinit var top1: View
    private lateinit var top2: View
    private lateinit var top3: View
    private lateinit var v1: View
    private lateinit var v2: View
    private lateinit var v3: View
    private lateinit var v4: View
    private lateinit var v5: View
    private lateinit var v6: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floatin_view4)

        top1 = findViewById(R.id.top1)
        top2 = findViewById(R.id.top2)
        top3 = findViewById(R.id.top3)

        v1 = findViewById(R.id.v1)
        v2 = findViewById(R.id.v2)

        v3 = findViewById(R.id.v3)
        v4 = findViewById(R.id.v4)

        v5 = findViewById(R.id.v5)
        v6 = findViewById(R.id.v6)

        init()
    }

    private fun init() {
        showFloatingView1()
        showFloatingView2()
        showFloatingView3()
    }

    private fun showFloatingView1() {
        val rootView = top1 as ViewGroup

        val floatingview = FloatingView1(rootView)

        rootView.addView(floatingview.getFloatingView())

        floatingview.getFloatingView().viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                floatingview.getFloatingView().viewTreeObserver.removeOnGlobalLayoutListener(this)

                val layoutParams = floatingview.getFloatingView().layoutParams as ConstraintLayout.LayoutParams

                layoutParams.width = MATCH_CONSTRAINT
                layoutParams.height = WRAP_CONTENT
                layoutParams.startToStart = R.id.v1
                layoutParams.leftMargin = 0
                layoutParams.endToEnd = R.id.v2
                layoutParams.rightMargin = 0
                layoutParams.topToTop = R.id.top1
                layoutParams.bottomToBottom = R.id.top1

                floatingview.getFloatingView().layoutParams = layoutParams

                Utils.slideingView(floatingview.getFloatingView())

                floatingview.setText1("테스트 메시지1", Color.parseColor("#ff9900"))
                floatingview.setText2("테스트 메시지2", Color.parseColor("#ff9900"))
            }
        })
        floatingview.getFloatingView().requestLayout()
    }

    private fun showFloatingView2() {
        val rootView = top2 as ViewGroup

        val floatingview = FloatingView2(rootView)

        rootView.addView(floatingview.getFloatingView())

        floatingview.getFloatingView().viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                floatingview.getFloatingView().viewTreeObserver.removeOnGlobalLayoutListener(this)

                val layoutParams = floatingview.getFloatingView().layoutParams as ConstraintLayout.LayoutParams

                layoutParams.width = MATCH_CONSTRAINT
                layoutParams.height = WRAP_CONTENT
                layoutParams.startToStart = R.id.v3
                layoutParams.leftMargin = v3.width
                layoutParams.endToEnd = R.id.v4
                layoutParams.rightMargin = v4.width
                layoutParams.topToTop = R.id.top2
                layoutParams.bottomToBottom = R.id.top2

                floatingview.getFloatingView().layoutParams = layoutParams

                Utils.slideingView(floatingview.getFloatingView())

                floatingview.setText1("테스트 메시지1", Color.parseColor("#ff9900"))
                floatingview.setText2("테스트 메시지2", Color.parseColor("#ff9900"))
            }
        })
        floatingview.getFloatingView().requestLayout()
    }

    private fun showFloatingView3() {
        val rootView = top3 as ViewGroup

        val floatingview = FloatingView3(rootView)

        rootView.addView(floatingview.getFloatingView())

        floatingview.getFloatingView().viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                floatingview.getFloatingView().viewTreeObserver.removeOnGlobalLayoutListener(this)

                val layoutParams = floatingview.getFloatingView().layoutParams as ConstraintLayout.LayoutParams

                layoutParams.width = MATCH_CONSTRAINT
                layoutParams.height = WRAP_CONTENT
                layoutParams.startToStart = R.id.v5
                layoutParams.leftMargin = v5.width + Utils.dip2px(this@FloatinView4Activity, 10f)
                layoutParams.endToEnd = R.id.v6
                layoutParams.rightMargin = v6.width + Utils.dip2px(this@FloatinView4Activity, 10f)
                layoutParams.topToTop = R.id.top3
                layoutParams.bottomToBottom = R.id.top3

                floatingview.getFloatingView().layoutParams = layoutParams

                Utils.slideingView(floatingview.getFloatingView())

                floatingview.setText1("테스트 메시지1", Color.parseColor("#ff9900"))
                floatingview.setText2("테스트 메시지2", Color.parseColor("#ff9900"))
            }
        })
        floatingview.getFloatingView().requestLayout()
    }

}