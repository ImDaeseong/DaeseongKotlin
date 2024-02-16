package com.daeseong.api_test

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class Api5Activity : AppCompatActivity() {

    private val tag: String = Api5Activity::class.java.simpleName

    private lateinit var tv1: TextView

    private val sUrl : String = "https://mypage.com/a.do"
    private val sParam : String = "{\"id\":\"test\",\"password\":\"test\",\"value\":\"mytestdata\"}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api5)

        tv1 = findViewById(R.id.tv1)

        //okhttp3
        func1()
    }



    //okhttp3 이용
    private fun func1() {

        OkHttpUtil.sendDataResultheader(sUrl, sParam, object : Callback {

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {

                    try {

                        val sResult = response.body?.string()
                        Log.e(tag, "sResult:$sResult")

                        runOnUiThread {
                            tv1.text = sResult
                        }

                    } catch (ex: Exception) {
                        Log.e(tag, ex.message.toString())
                    }

                } else {
                    Log.e(tag, "response.code:" + response.code)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e(tag, "onFailure:" + e.message)
            }

        })

    }
    //okhttp3 이용



}