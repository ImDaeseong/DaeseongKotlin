package com.daeseong.tablestyle_test

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Main4Activity : AppCompatActivity(), OnTouchListener {

    private val tag = Main4Activity::class.java.simpleName

    private var tl1 : View? =  null
    private var tlmenu : View? = null
    private var clB1 : View? = null
    private var clcontent : View? = null

    private var title: TextView? = null
    private var sv1: ScrollView? = null
    private var hs1: HorizontalScrollView? = null
    private var rl1: RelativeLayout? = null

    private val nCount = 200
    private val nButtonWidth = 200
    private val nButtonHeight = 100

    private var nlastX = 0
    private var nlastY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_main4)

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
        clcontent = findViewById<View>(R.id.clcontent)

        sv1 = findViewById<ScrollView>(R.id.sv1)
        hs1 = findViewById<HorizontalScrollView>(R.id.hs1)
        rl1 = findViewById<RelativeLayout>(R.id.rl1)

        sv1!!.setFadingEdgeLength(0)
        hs1!!.setFadingEdgeLength(0)
        sv1!!.setOnTouchListener(this)
        hs1!!.setOnTouchListener(this)
    }

    private fun initData() {

        rl1!!.removeAllViews()

        rl1!!.minimumWidth = 2200
        rl1!!.minimumHeight = 2200

        val params = RelativeLayout.LayoutParams(nButtonWidth, nButtonHeight)
        params.setMargins(0, 0, 0, 0)

        for (i in 0 until nCount) {

            val sNum = String.format("%d", i)
            var nX = 0
            var nY = 0
            val nDiv = 10

            if (i == 0) {

                nY = 0
                nX = 0

            } else {

                val nGapWidth = i % nDiv
                val nGapHeight = i / nDiv
                nX = nGapWidth * nButtonWidth
                nY = nGapHeight * nButtonHeight
            }

            val btn = Button(this)
            btn.layoutParams = params
            btn.setBackgroundResource(R.drawable.btnbordero)
            btn.isEnabled = true
            btn.minimumHeight = nButtonWidth
            btn.minimumWidth = nButtonHeight
            btn.setPadding(0, 0, 0, 0)
            btn.x = nX.toFloat()
            btn.y = nY.toFloat()
            btn.textSize = 14f
            btn.text = String.format("번호%d", i)
            btn.setOnClickListener(btnClick)
            btn.setOnTouchListener(btnTouch)
            rl1!!.addView(btn)
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

    private fun changeScroll(x: Int, y: Int) {
        hs1!!.scrollBy(x, 0)
        sv1!!.scrollBy(0, y)
    }

    @SuppressLint("ClickableViewAccessibility")
    var btnTouch = OnTouchListener { _, event ->

        when (event.action) {

            MotionEvent.ACTION_MOVE -> {

                val nX = event.rawX.toInt()
                val nY = event.rawY.toInt()
                changeScroll(nlastX - nX, nlastY - nY)
                nlastX = nX
                nlastY = nY
            } MotionEvent.ACTION_UP -> {

            } else -> {

                nlastX = event.rawX.toInt()
                nlastY = event.rawY.toInt()
            }
        }

        false
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                val nX = event.rawX.toInt()
                val nY = event.rawY.toInt()
                changeScroll(nlastX - nX, nlastY - nY)
                nlastX = nX
                nlastY = nY

            } MotionEvent.ACTION_UP -> {

            } else -> {
                nlastX = event.rawX.toInt()
                nlastY = event.rawY.toInt()
            }

        }

        nlastX = event.rawX.toInt()
        nlastY = event.rawY.toInt()
        return true
    }
}