package com.daeseong.flexboxlayout_test

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.flexboxlayout_test.UnitUtils.dip2px
import com.google.android.flexbox.FlexboxLayout

class FlexboxLayout4Activity : AppCompatActivity() {

    private val tag = FlexboxLayout4Activity::class.java.simpleName

    private var cl1: View? = null
    private var cl2: View? = null
    private var cl3: View? = null
    private var cl4: View? = null
    private var cl5: View? = null
    private var t1: TextView? = null
    private var t2: TextView? = null
    private var t3: TextView? = null
    private var t4: TextView? = null
    private var t5: TextView? = null

    private var cl11: View? = null
    private var cl22: View? = null
    private var cl33: View? = null
    private var cl44: View? = null
    private var cl55: View? = null
    private var t11: TextView? = null
    private var t22: TextView? = null
    private var t33: TextView? = null
    private var t44: TextView? = null
    private var t55: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexbox_layout4)

        init()

        initData()
    }

    private fun init() {

        cl1 = findViewById(R.id.cl1)
        cl2 = findViewById(R.id.cl2)
        cl3 = findViewById(R.id.cl3)
        cl4 = findViewById(R.id.cl4)
        cl5 = findViewById(R.id.cl5)

        t1 = findViewById<TextView>(R.id.t1)
        t2 = findViewById<TextView>(R.id.t2)
        t3 = findViewById<TextView>(R.id.t3)
        t4 = findViewById<TextView>(R.id.t4)
        t5 = findViewById<TextView>(R.id.t5)

        cl11 = findViewById(R.id.cl11)
        cl22 = findViewById(R.id.cl22)
        cl33 = findViewById(R.id.cl33)
        cl44 = findViewById(R.id.cl44)
        cl55 = findViewById(R.id.cl55)

        t11 = findViewById<TextView>(R.id.t11)
        t22 = findViewById<TextView>(R.id.t22)
        t33 = findViewById<TextView>(R.id.t33)
        t44 = findViewById<TextView>(R.id.t44)
        t55 = findViewById<TextView>(R.id.t55)
    }

    private fun initData() {

        t1!!.text = "테스트 데이터1"
        t2!!.text = "데이터2"
        t3!!.text = "test 데이터3"
        t4!!.text = "테스트 데이터4"
        t5!!.text = "데이터5"

        t11!!.text = "테스트 데이터1"
        t22!!.text = "데이터2"
        t33!!.text = "test 데이터3"
        t44!!.text = "테스트 데이터4"
        t55!!.text = "데이터5"

        val nMarginRight = dip2px(this@FlexboxLayout4Activity, 4f)

        val layoutParams1 = cl1!!.layoutParams as FlexboxLayout.LayoutParams
        layoutParams1.rightMargin = nMarginRight
        cl1!!.layoutParams = layoutParams1

        val layoutParams2 = cl2!!.layoutParams as FlexboxLayout.LayoutParams
        layoutParams2.rightMargin = nMarginRight
        cl2!!.layoutParams = layoutParams2

        val layoutParams3 = cl3!!.layoutParams as FlexboxLayout.LayoutParams
        layoutParams3.rightMargin = nMarginRight
        cl3!!.layoutParams = layoutParams3

        val layoutParams4 = cl4!!.layoutParams as FlexboxLayout.LayoutParams
        layoutParams4.rightMargin = nMarginRight
        cl4!!.layoutParams = layoutParams4

        val layoutParams5 = cl5!!.layoutParams as FlexboxLayout.LayoutParams
        layoutParams5.rightMargin = nMarginRight
        cl5!!.layoutParams = layoutParams5


        val layoutParams11 = cl11!!.layoutParams as FlexboxLayout.LayoutParams
        layoutParams11.topMargin = nMarginRight
        layoutParams11.rightMargin = nMarginRight * 2
        cl11!!.layoutParams = layoutParams11

        val layoutParams22 = cl22!!.layoutParams as FlexboxLayout.LayoutParams
        layoutParams22.topMargin = nMarginRight
        layoutParams22.rightMargin = nMarginRight * 2
        cl22!!.layoutParams = layoutParams22

        val layoutParams33 = cl33!!.layoutParams as FlexboxLayout.LayoutParams
        layoutParams33.topMargin = nMarginRight
        layoutParams33.rightMargin = nMarginRight * 2
        cl33!!.layoutParams = layoutParams33

        val layoutParams44 = cl44!!.layoutParams as FlexboxLayout.LayoutParams
        layoutParams44.topMargin = nMarginRight
        layoutParams44.rightMargin = nMarginRight * 2
        cl44!!.layoutParams = layoutParams44

        val layoutParams55 = cl55!!.layoutParams as FlexboxLayout.LayoutParams
        layoutParams55.topMargin = nMarginRight
        layoutParams55.rightMargin = nMarginRight * 2
        cl55!!.layoutParams = layoutParams55
    }
}