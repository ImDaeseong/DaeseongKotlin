package com.daeseong.okhttpclient_test

import HttpUtilOK
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import HttpUtilOKObj
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

        getLogin(this, "https://nid.naver.com/nidlogin.login", "", "")
        getBTC(this, "https://api.bithumb.com/public/ticker/BTC")
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

                    val res = response.body!!.string()
                    Log.e(tag, res)

                    val resultObj = JSONObject(res)
                    var status = resultObj.getString("status")
                    if (TextUtils.isEmpty(status)) {
                        status = ""
                    }

                    var data = resultObj.getString("data")
                    if (TextUtils.isEmpty(data)) {
                        data = ""
                    }

                    if (status == "0000") {
                        val resultData = JSONObject(data)
                        var opening_price = resultData.getString("opening_price")
                        if (TextUtils.isEmpty(opening_price)) {
                            opening_price = ""
                        }
                        //Log.e(tag, opening_price);

                        var closing_price = resultData.getString("closing_price")
                        if (TextUtils.isEmpty(closing_price)) {
                            closing_price = ""
                        }
                        //Log.e(tag, closing_price);

                        var min_price = resultData.getString("min_price")
                        if (TextUtils.isEmpty(min_price)) {
                            min_price = ""
                        }
                        //Log.e(tag, min_price);

                        var max_price = resultData.getString("max_price")
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

    private fun getLogin(context: Context, address: String, ID: String, PWD: String) {

        HttpUtilOK().getLogin(address, ID, PWD, object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Log.e(tag, "getlogin onFailure")
                }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                try {
                    val body = response.body!!.string()
                    Log.e(tag, body)
                } catch (ex: java.lang.Exception) {
                }
            }
        })
    }

    private fun getDataResult() {

        val sUrl = "https://nid.naver.com/nidlogin.login"

        HttpUtilOKObj.getDataResult(sUrl, object : Callback{

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {

                } else {

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e(tag, e.message.toString());
            }
        })
    }

    private fun sendDataResult() {

        val sUrl = "https://nid.naver.com/nidlogin.login"
        val sParam : String = String.format("{\"id\":\"%s\",\"password\":\"%s\"}", "id", "password")

        HttpUtilOKObj.sendDataResult(sUrl, sParam, object : Callback{

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {

                } else {

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e(tag, e.message.toString());
            }
        })

    }
}
