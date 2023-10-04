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
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main4Activity : AppCompatActivity(), View.OnTouchListener {

    private val tag = Main4Activity::class.java.simpleName

    private lateinit var tl1: View
    private lateinit var tlmenu: View
    private lateinit var clB1: View
    private lateinit var clcontent: View

    private var title: TextView? = null
    private lateinit var sv1: ScrollView
    private lateinit var hs1: HorizontalScrollView
    private lateinit var rl1: RelativeLayout

    private val nCount = 200
    private val nButtonWidth = 200
    private val nButtonHeight = 100

    private var nlastX = 0
    private var nlastY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTitleBar()

        setContentView(R.layout.activity_main4)

        init()

        initConfiguration()

        initData()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        toggleViewsVisibility(newConfig.orientation)
    }

    private fun initConfiguration() {
        val configuration = Resources.getSystem().configuration
        toggleViewsVisibility(configuration.orientation)
    }

    private fun toggleViewsVisibility(orientation: Int) {
        val visibility = if (orientation == Configuration.ORIENTATION_LANDSCAPE) View.GONE else View.VISIBLE
        tl1.visibility = visibility
        tlmenu.visibility = visibility
        clB1.visibility = visibility
    }

    private fun initTitleBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.rgb(255, 255, 255)
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    private fun init() {
        tl1 = findViewById(R.id.tl1)
        tlmenu = findViewById(R.id.tlmenu)
        clB1 = findViewById(R.id.clB1)
        clcontent = findViewById(R.id.clcontent)

        sv1 = findViewById(R.id.sv1)
        hs1 = findViewById(R.id.hs1)
        rl1 = findViewById(R.id.rl1)

        sv1.setOnTouchListener(this)
        hs1.setOnTouchListener(this)
    }

    private fun initData() {
        rl1.removeAllViews()

        rl1.minimumWidth = 2200
        rl1.minimumHeight = 2200

        val params = RelativeLayout.LayoutParams(nButtonWidth, nButtonHeight)
        params.setMargins(0, 0, 0, 0)

        for (i in 0 until nCount) {
            val sNum = String.format("%d", i)
            var nX = 0
            var nY = 0
            val nDiv = 10

            if (i != 0) {
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
            rl1.addView(btn)
        }
    }

    private val btnClick = View.OnClickListener { v ->
        val btn = v as Button
        val sText = btn.text.toString()
        Log.e(tag, sText)
    }

    private fun changeScroll(x: Int, y: Int) {
        hs1.scrollBy(x, 0)
        sv1.scrollBy(0, y)
    }

    @SuppressLint("ClickableViewAccessibility")
    private val btnTouch = View.OnTouchListener { _, event ->
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                val nX = event.rawX.toInt()
                val nY = event.rawY.toInt()
                changeScroll(nlastX - nX, nlastY - nY)
                nlastX = nX
                nlastY = nY
            }
            MotionEvent.ACTION_UP -> {

            }
            else -> {
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
            }
            MotionEvent.ACTION_UP -> {

            }
            else -> {
                nlastX = event.rawX.toInt()
                nlastY = event.rawY.toInt()
            }
        }
        return true
    }
}
