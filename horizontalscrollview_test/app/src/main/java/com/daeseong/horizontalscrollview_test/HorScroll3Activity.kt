package com.daeseong.horizontalscrollview_test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class HorScroll3Activity : AppCompatActivity() {

    private val tag = HorScroll3Activity::class.java.simpleName

    private lateinit var LL1: LinearLayout
    private lateinit var cL3: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hor_scroll3)

        init()
        initData()
    }

    private fun init() {

        LL1 = findViewById(R.id.LL1)
        cL3 = findViewById(R.id.cL3)
        cL3.setOnClickListener {

        }
    }

    private fun initData() {

        for (i in 0 until 10) {

            val clview = LayoutInflater.from(this).inflate(R.layout.image_scroll_item, null) as ConstraintLayout
            clview.tag = i
            clview.setOnClickListener(cLClicked)

            //이미지
            val ivImg = clview.findViewById<View>(R.id.ivreservation) as ImageView

            //마진 설정
            val params = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, UnitUtils.dip2px(this, 6), 0)
            clview.layoutParams = params

            //추가
            LL1.addView(clview)
        }
    }

    private val cLClicked = View.OnClickListener { v ->
        try {
            val nIndex = v.tag as Int
            Log.e(tag, nIndex.toString())
        } catch (ex: Exception) {
        }
    }
}