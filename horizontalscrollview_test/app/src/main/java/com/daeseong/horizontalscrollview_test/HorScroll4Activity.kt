package com.daeseong.horizontalscrollview_test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class HorScroll4Activity : AppCompatActivity() {

    private val tag = HorScroll4Activity::class.java.simpleName

    private var cl1: View? = null
    private var cl2: View? = null
    private var cl3: View? = null
    private var cl4: View? = null
    private var cl5: View? = null
    private var cl6: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hor_scroll4)

        cl1 = findViewById<View>(R.id.cl1)
        cl1!!.setOnClickListener { Log.d(tag, "cl1") }

        cl2 = findViewById<View>(R.id.cl2)
        cl2!!.setOnClickListener { Log.d(tag, "cl2") }

        cl3 = findViewById<View>(R.id.cl3)
        cl3!!.setOnClickListener { Log.d(tag, "cl3") }

        cl4 = findViewById<View>(R.id.cl4)
        cl4!!.setOnClickListener { Log.d(tag, "cl4") }

        cl5 = findViewById<View>(R.id.cl5)
        cl5!!.setOnClickListener { Log.d(tag, "cl5") }

        cl6 = findViewById<View>(R.id.cl6)
        cl6!!.setOnClickListener { Log.d(tag, "cl6") }
    }
}