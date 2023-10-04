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

    private lateinit var tl1: View
    private lateinit var tlmenu: View
    private lateinit var clB1: View

    private lateinit var gl: GridLayout

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

        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE, Configuration.ORIENTATION_PORTRAIT -> toggleViewsVisibility(newConfig.orientation)
        }
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

    private fun InitTitleBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.rgb(255, 255, 255)
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    private fun init() {

        tl1 = findViewById(R.id.tl1)
        tlmenu = findViewById(R.id.tlmenu)
        clB1 = findViewById(R.id.clB1)
        gl = findViewById(R.id.gl)
    }

    private fun initData() {

        gl.removeAllViews()

        gl.rowCount = nRowCount
        gl.columnCount = nColumnCount

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
                gl.addView(btn)
            }
        }
    }

    //버튼 클릭 이벤트
    private val btnClick = View.OnClickListener { v ->
        val btn = v as Button
        val sText = btn.text.toString()
        Log.e(tag, sText)
    }
}