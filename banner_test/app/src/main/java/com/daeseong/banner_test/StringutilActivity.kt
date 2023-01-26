package com.daeseong.banner_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StringutilActivity : AppCompatActivity(), View.OnClickListener {

    private val tag: String = StringutilActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null
    private var button5 : Button? = null
    private var button6 : Button? = null
    private var button7 : Button? = null
    private var button8 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stringutil)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener(this)

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener(this)

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener(this)

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener(this)

        button5 = findViewById<Button>(R.id.button5)
        button5!!.setOnClickListener(this)

        button6 = findViewById<Button>(R.id.button6)
        button6!!.setOnClickListener(this)

        button7 = findViewById<Button>(R.id.button7)
        button7!!.setOnClickListener(this)

        button8 = findViewById<Button>(R.id.button8)
        button8!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v != null) {

            when (v.id) {

                R.id.button1 -> {

                    val sInput = "2023-01-26"
                    val sResult = String_util.getLastVisitDay(sInput)
                    Log.e(tag, sResult!!)
                }

                R.id.button2 -> {

                    val sInput = "2023-01-25"
                    val sResult = String_util.getLastVisitDay(sInput)
                    Log.e(tag, sResult!!)
                }

                R.id.button3 -> {

                    val sInput = "2023-01-06"
                    val sResult = String_util.getLastVisitDay(sInput)
                    Log.e(tag, sResult!!)
                }

                R.id.button4 -> {

                    val sInput = "2022-11-06"
                    val sResult = String_util.getLastVisitDay(sInput)
                    Log.e(tag, sResult!!)
                }

                R.id.button5 -> {

                    val sInput = "2022-10-28"
                    val sResult = String_util.getLastVisitDay(sInput)
                    Log.e(tag, sResult!!)

                }

                R.id.button6 -> {

                    val sInput = "2022-08-16"
                    val sResult = String_util.getLastVisitDay(sInput)
                    Log.e(tag, sResult!!)
                }

                R.id.button7 -> {

                    val sInput = "2021-08-08"
                    val sResult = String_util.getLastVisitDay(sInput)
                    Log.e(tag, sResult!!)
                }

                R.id.button8 -> {

                    val sInput = "2020-08-10"
                    val sResult = String_util.getLastVisitDay(sInput)
                    Log.e(tag, sResult!!)
                }

            }
        }
    }
}