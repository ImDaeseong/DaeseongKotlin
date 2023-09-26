package com.daeseong.banner_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StringutilActivity : AppCompatActivity(), View.OnClickListener {

    private val tag: String = StringutilActivity::class.java.simpleName

    private lateinit var buttons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stringutil)

        buttons = listOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8)
        )

        buttons.forEach { button ->
            button.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.button1 -> processButtonClick(1)
                R.id.button2 -> processButtonClick(2)
                R.id.button3 -> processButtonClick(3)
                R.id.button4 -> processButtonClick(4)
                R.id.button5 -> processButtonClick(5)
                R.id.button6 -> processButtonClick(6)
                R.id.button7 -> processButtonClick(7)
                R.id.button8 -> processButtonClick(8)
            }
        }
    }

    private fun processButtonClick(nType: Int) {

        when (nType) {

            1 -> {

                val sInput = "2023-01-26"
                val sResult = StringUtil.getLastVisitDay(sInput)
                Log.e(tag, sResult!!)
            }

            2 -> {

                val sInput = "2023-01-25"
                val sResult = StringUtil.getLastVisitDay(sInput)
                Log.e(tag, sResult!!)
            }

            3 -> {

                val sInput = "2023-01-06"
                val sResult = StringUtil.getLastVisitDay(sInput)
                Log.e(tag, sResult!!)
            }

            4 -> {

                val sInput = "2022-11-06"
                val sResult = StringUtil.getLastVisitDay(sInput)
                Log.e(tag, sResult!!)
            }

            5 -> {

                val sInput = "2022-10-28"
                val sResult = StringUtil.getLastVisitDay(sInput)
                Log.e(tag, sResult!!)

            }

            6 -> {

                val sInput = "2022-08-16"
                val sResult = StringUtil.getLastVisitDay(sInput)
                Log.e(tag, sResult!!)
            }

            7 -> {

                val sInput = "2021-08-08"
                val sResult = StringUtil.getLastVisitDay(sInput)
                Log.e(tag, sResult!!)
            }

            8 -> {

                val sInput = "2020-08-10"
                val sResult = StringUtil.getLastVisitDay(sInput)
                Log.e(tag, sResult!!)
            }
        }
    }

}