package com.daeseong.api_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class Api7Activity : AppCompatActivity() {

    private val tag: String = Api7Activity::class.java.simpleName

    private val sUrl : String = "https://api.bithumb.com/public/orderbook/BTC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api7)

        //okhttp3
        func1()
    }

    //okhttp3 이용
    private fun func1() {

        OkHttpUtil.getDataResult(sUrl, object : Callback {

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {

                    try {

                        val sResult = response.body?.string()
                        Log.e(tag, "sResult:$sResult")

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