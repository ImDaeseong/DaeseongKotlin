package com.daeseong.flexboxlayout_test

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.daeseong.flexboxlayout_test.UnitUtils.dip2px
import com.google.android.flexbox.FlexboxLayout

class FlexboxLayout3Activity : AppCompatActivity() {

    private val tag = FlexboxLayout3Activity::class.java.simpleName

    private lateinit var btn1: Button
    private lateinit var fL1: FlexboxLayout
    private var cLBG: ConstraintLayout? = null
    private var tv1: TextView? = null

    private val checkMap = HashMap<Int, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexbox_layout3)

        init()

        //initTest()

        initData()
    }

    private fun init() {

        btn1 = findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener {
            for (i in 0 until checkMap.size) {
                Log.e(tag, "select: $i ${checkMap[i]}")
            }
        }

        fL1 = findViewById<FlexboxLayout>(R.id.fL1)
    }

    private fun initTest() {

        val inflater = LayoutInflater.from(this)
        cLBG = inflater.inflate(R.layout.flexbox_item, null) as ConstraintLayout
        cLBG!!.setBackgroundResource(R.drawable.border_normal)
        cLBG!!.setOnClickListener(flexClicked)

        tv1 = cLBG!!.findViewById(R.id.tv1)
        tv1!!.setTextColor(Color.parseColor("#8B8E97"))
        tv1!!.setTypeface(null, Typeface.BOLD)
        tv1!!.text = "testtesst"

        val params = FlexboxLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val marginLeft = dip2px(this, 6f)
        val marginTop = dip2px(this, 8f)
        params.setMargins(marginLeft, marginTop, 0, 0)
        cLBG!!.layoutParams = params
        fL1.addView(cLBG)
    }

    private fun initData() {

        fL1!!.removeAllViews()
        checkMap.clear()

        for (i in 0..9) {

            cLBG = LayoutInflater.from(this).inflate(R.layout.flexbox_item, null) as ConstraintLayout
            cLBG!!.setBackgroundResource(R.drawable.border_normal)
            cLBG!!.setOnClickListener(flexClicked)
            cLBG!!.tag = i

            tv1 = cLBG!!.findViewById<View>(R.id.tv1) as TextView
            tv1!!.setTextColor(Color.parseColor("#8B8E97"))
            tv1!!.setTypeface(null, Typeface.NORMAL)
            tv1!!.text = String.format("Index:%d", i)

            val params = FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val marginLeft = dip2px(this, 6f)
            val marginTop = dip2px(this, 8f)
            params.setMargins(marginLeft, marginTop, 0, 0)
            cLBG!!.layoutParams = params
            fL1!!.addView(cLBG)

            checkMap[i] = false
        }
    }

    var flexClicked = View.OnClickListener { v ->

        try {

            for (i in 0 until fL1.childCount) {

                val cL = fL1.getChildAt(i) as ConstraintLayout
                if (v.tag === cL.tag) {

                    var nTextColor: Int
                    var nTextStyle: Int

                    if (checkMap[i] == true) {

                        checkMap[i] = false
                        cL.setBackgroundResource(R.drawable.border_normal)
                        nTextColor = Color.parseColor("#8B8E97")
                        nTextStyle = Typeface.NORMAL

                    } else {

                        checkMap[i] = true
                        cL.setBackgroundResource(R.drawable.border_select)
                        nTextColor = Color.parseColor("#333333")
                        nTextStyle = Typeface.BOLD

                    }

                    for (j in 0 until cL.childCount) {

                        val v_textview = cL.getChildAt(j)
                        if (v_textview is TextView) {
                            val tv = v_textview
                            tv.setTextColor(nTextColor)
                            tv.setTypeface(null, nTextStyle)
                        }
                    }
                }
            }

        } catch (ex: Exception) {
        }
    }

}