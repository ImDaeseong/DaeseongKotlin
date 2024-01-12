package com.daeseong.floatingview_test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout

class FloatinView3Activity : AppCompatActivity() {

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
        setContentView(R.layout.activity_floatin_view3)

        top1 = findViewById(R.id.top1)
        top2 = findViewById(R.id.top2)
        top3 = findViewById(R.id.top3)

        v1 = findViewById(R.id.v1)
        v2 = findViewById(R.id.v2)
        v2.setOnClickListener {
            showFloatingView1()
        }

        v3 = findViewById(R.id.v3)
        v4 = findViewById(R.id.v4)
        v4.setOnClickListener {
            showFloatingView2()
        }

        v5 = findViewById(R.id.v5)
        v6 = findViewById(R.id.v6)
        v6.setOnClickListener {
            showFloatingView3()
        }

        init()
    }

    private fun init() {
    }

    private fun showFloatingView1() {
        val rootView: ViewGroup = top1 as ViewGroup

        val floatingView = FloatingView3(rootView)
        floatingView.getFloatingView().visibility = View.VISIBLE

        val layoutParams = floatingView.getFloatingView().layoutParams as ConstraintLayout.LayoutParams

        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
        layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT

        layoutParams.startToStart = R.id.v1
        layoutParams.leftMargin = 0

        layoutParams.endToEnd = R.id.v2
        layoutParams.rightMargin = 0

        layoutParams.topToTop = R.id.top1
        layoutParams.bottomToBottom = R.id.top1

        floatingView.getFloatingView().layoutParams = layoutParams
        rootView.addView(floatingView.getFloatingView())

        floatingView.setText1("테스트 메시지1", Color.parseColor("#ff9900"))
        floatingView.setText2("테스트 메시지2", Color.parseColor("#ff9900"))

    }

    private fun showFloatingView2() {
        val rootView: ViewGroup = top2 as ViewGroup

        val floatingView = FloatingView3(rootView)
        floatingView.getFloatingView().visibility = View.VISIBLE

        val layoutParams = floatingView.getFloatingView().layoutParams as ConstraintLayout.LayoutParams

        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
        layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT

        layoutParams.startToStart = R.id.v3
        layoutParams.leftMargin = v3.width

        layoutParams.endToEnd = R.id.v4
        layoutParams.rightMargin = v4.width

        layoutParams.topToTop = R.id.top2
        layoutParams.bottomToBottom = R.id.top2

        floatingView.getFloatingView().layoutParams = layoutParams
        rootView.addView(floatingView.getFloatingView())

        floatingView.setText1("테스트 메시지1", Color.parseColor("#ff9900"))
        floatingView.setText2("테스트 메시지2", Color.parseColor("#ff9900"))

    }

    private fun showFloatingView3() {
        val rootView: ViewGroup = top3 as ViewGroup

        val floatingView = FloatingView3(rootView)
        floatingView.getFloatingView().visibility = View.VISIBLE

        val layoutParams = floatingView.getFloatingView().layoutParams as ConstraintLayout.LayoutParams

        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
        layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT

        layoutParams.startToStart = R.id.v5
        layoutParams.leftMargin = v5.width + Utils.dip2px(this, 10f)

        layoutParams.endToEnd = R.id.v6
        layoutParams.rightMargin = v6.width + Utils.dip2px(this, 10f)

        layoutParams.topToTop = R.id.top3
        layoutParams.bottomToBottom = R.id.top3

        floatingView.getFloatingView().layoutParams = layoutParams
        rootView.addView(floatingView.getFloatingView())

        floatingView.setText1("테스트 메시지1", Color.parseColor("#ff9900"))
        floatingView.setText2("테스트 메시지2", Color.parseColor("#ff9900"))
    }
}