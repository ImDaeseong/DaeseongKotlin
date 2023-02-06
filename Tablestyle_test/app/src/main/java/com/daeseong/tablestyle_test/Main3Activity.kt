package com.daeseong.tablestyle_test

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity

class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName

    private var tl1: View? = null
    private var tlmenu : View? = null
    private var clB1 : View? = null

    private var gl: GridLayout? = null

    private val nRowCount = 20
    private val nColumnCount = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_main3)

        init()

        initConfiguration()

        initData()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //Log.e(tag, "가로 방향")

            tl1!!.visibility = View.GONE
            tlmenu!!.visibility = View.GONE
            clB1!!.visibility = View.GONE

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            //Log.e(tag, "세로 방향")

            tl1!!.visibility = View.VISIBLE
            tlmenu!!.visibility = View.VISIBLE
            clB1!!.visibility = View.VISIBLE
        }
    }

    private fun initConfiguration() {

        val configuration = Resources.getSystem().configuration
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //Log.e(tag, "가로 방향")

            tl1!!.visibility = View.GONE
            tlmenu!!.visibility = View.GONE
            clB1!!.visibility = View.GONE

        } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

            //Log.e(tag, "세로 방향")

            tl1!!.visibility = View.VISIBLE
            tlmenu!!.visibility = View.VISIBLE
            clB1!!.visibility = View.VISIBLE
        }
    }

    private fun InitTitleBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.rgb(255, 255, 255)
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    private fun init() {
        tl1 = findViewById<View>(R.id.tl1)
        tlmenu = findViewById<View>(R.id.tlmenu)
        clB1 = findViewById<View>(R.id.clB1)

        gl = findViewById<GridLayout>(R.id.gl)
    }

    private fun initData() {

        gl!!.removeAllViews()

        gl!!.rowCount = nRowCount
        gl!!.columnCount = nColumnCount

        var nIndex = 1
        for (i in 0 until nRowCount) {
            for (k in 0 until nColumnCount) {

                val btn = Button(this)
                btn.setPadding(0, 0, 0, 0)
                btn.id = nIndex
                btn.setBackgroundResource(R.drawable.btnbordero)
                btn.setTextColor(Color.BLACK)
                btn.textSize = 14f
                btn.text = String.format("번호%d", nIndex)
                btn.setOnClickListener(btnClick)
                nIndex++
                gl!!.addView(btn)
            }
        }
    }

    //버튼 클릭 이벤트
    var btnClick = View.OnClickListener { v ->

        //Log.e(tag, v.id.toString())

        //버튼 텍스트
        val btn = v as Button
        val sText = btn.text.toString()
        //Log.e(tag, sText)
    }
}