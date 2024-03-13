package com.daeseong.paging_test

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.paging_test.Common.HttpUtilOK
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class Main2Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private lateinit var tv1: TextView
    private lateinit var button1: Button

    private var sResult = ""

    private var sUrl = ""
    private val sSearchkey = "android"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tv1 = findViewById<TextView>(R.id.tv1)
        button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {

            try {

                sUrl = String.format("%s&q=%s", ConstantsUrl.sUrl1, sSearchkey )
                getDataInfo(sUrl, tv1)

            } catch (ex: Exception) {
                Log.e(tag, ex.message.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        HttpUtilOK.cancelAll()
    }

    private fun getDataInfo(sUrl: String, tv: TextView) {

        val callback = object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val sResult = response.body?.string() ?: ""
                Log.e(tag, "sResult: $sResult")
                tv.post { tv.text = sResult }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e(tag, "onFailure: ${e.message}")
            }
        }
        HttpUtilOK.getData(sUrl, callback)
    }

}