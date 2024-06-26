package com.daeseong.floatingview_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)

        topFloating()
        bottomFloating()
    }

    override fun onClick(v: View?) {
        v?.let {
            val intent = when (it.id) {
                R.id.button1 -> Intent(this, FloatinView1Activity::class.java)
                R.id.button2 -> Intent(this, FloatinView2Activity::class.java)
                R.id.button3 -> Intent(this, FloatinView3Activity::class.java)
                R.id.button4 -> Intent(this, FloatinView4Activity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }
    }


    //화면 하단 플로팅뷰
    private fun bottomFloating() {

        try {
            val rootView = findViewById<ViewGroup>(android.R.id.content)
            val floatingImgBottomLayout = FloatingImgBottomLayout(this)
            floatingImgBottomLayout.show(rootView)

            val floatingView = floatingImgBottomLayout.getFloatingView()

            floatingView.setOnClickListener(object : OnSingleClickListener()  {
                override fun onSingleClick(view: View) {
                    try {
                        startActivity(Intent(this@MainActivity, FloatinView1Activity::class.java))
                        floatingImgBottomLayout.hide()
                    } catch (ex:Exception) {
                    }
                }
            })
        } catch (ex:Exception) {
        }
    }

    private fun topFloating() {

        val rootView = findViewById<ViewGroup>(android.R.id.content)
        val floatingImgTopLayout = FloatingImgTopLayout(this)
        floatingImgTopLayout.show(rootView, R.id.cL1)

        /*
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        val floatingDoubleImgTopLayout = FloatingDoubleImgTopLayout(this)
        floatingDoubleImgTopLayout.show(rootView, R.id.cL1)
        */
    }
}