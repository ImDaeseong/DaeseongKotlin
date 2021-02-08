package com.daeseong.okhttpclient_test

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.okhttpclient_test.common.HttpUtilOK
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getlogin(this, "https://nid.naver.com/nidlogin.login", "", "");
        getBTC(this, "https://api.bithumb.com/public/ticker/BTC");
    }

    private fun getBTC(context: Context, address: String) {

        HttpUtilOK().getBTC(address, object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Log.e(tag, "getBTC onFailure")
                }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {

                try {

                    val res = response.body().string()
                    //Log.e(tag, res)

                    val resultObj = JSONObject(res)
                    var status = resultObj["status"].toString()
                    if (TextUtils.isEmpty(status)) {
                        status = ""
                    }

                    var data = resultObj["data"].toString()
                    if (TextUtils.isEmpty(data)) {
                        data = ""
                    }

                    if (status == "0000") {
                        val resultData = JSONObject(data)
                        var opening_price = resultData["opening_price"].toString()
                        if (TextUtils.isEmpty(opening_price)) {
                            opening_price = ""
                        }
                        //Log.e(tag, opening_price);

                        var closing_price = resultData["closing_price"].toString()
                        if (TextUtils.isEmpty(closing_price)) {
                            closing_price = ""
                        }
                        //Log.e(tag, closing_price);

                        var min_price = resultData["min_price"].toString()
                        if (TextUtils.isEmpty(min_price)) {
                            min_price = ""
                        }
                        //Log.e(tag, min_price);

                        var max_price = resultData["max_price"].toString()
                        if (TextUtils.isEmpty(max_price)) {
                            max_price = ""
                        }
                        //Log.e(tag, max_price);

                    } else {
                        Log.e(tag, "서버 데이터 가져오기 에러")
                    }
                } catch (ex: Exception) {
                }
            }
        })
    }

    private fun getlogin(context: Context, address: String, ID: String, PWD: String) {

        HttpUtilOK().getlogin(address, ID, PWD, object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Log.e(tag, "getlogin onFailure")
                }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                try {
                    val body = response.body().string()
                    //Log.e(tag, body)
                } catch (ex: java.lang.Exception) {
                }
            }
        })
    }
}
