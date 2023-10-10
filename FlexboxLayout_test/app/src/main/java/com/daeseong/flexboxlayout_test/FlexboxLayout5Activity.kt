package com.daeseong.flexboxlayout_test

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.daeseong.flexboxlayout_test.UnitUtils.dip2px
import com.google.android.flexbox.FlexboxLayout

class FlexboxLayout5Activity : AppCompatActivity() {

    private val tag = FlexboxLayout5Activity::class.java.simpleName

    private lateinit var fL1: FlexboxLayout
    private lateinit var tv1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexbox_layout5)

        init()

        initData()
    }

    private fun init() {
        fL1 = findViewById(R.id.fL1)
    }

    private fun initData() {

        fL1.removeAllViews()

        for (i in 0..9) {
            val cl = LayoutInflater.from(this@FlexboxLayout5Activity).inflate(R.layout.flexbox_item, null) as ConstraintLayout
            cl.setOnClickListener(flexClicked)
            cl.tag = i

            tv1 = cl.findViewById(R.id.tv1) as TextView
            tv1.setTextColor(Color.parseColor("#8B8E97"))
            tv1.setTypeface(null, Typeface.NORMAL)

            tv1.text = if (i % 2 == 0) {
                String.format("Index Index:%d", i)
            } else {
                String.format("Index:%d", i)
            }

            val params = FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val marginLeft = dip2px(this, 6f)
            val marginTop = dip2px(this, 8f)
            params.setMargins(marginLeft, marginTop, 0, 0)
            cl.layoutParams = params
            fL1.addView(cl)
        }
    }

    var flexClicked = View.OnClickListener {

    }
}