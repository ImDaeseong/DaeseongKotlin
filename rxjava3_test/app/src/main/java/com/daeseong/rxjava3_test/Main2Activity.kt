package com.daeseong.rxjava3_test

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private var textView1: TextView? = null

    private val sUrl = "https://api.bithumb.com/public/ticker/BTC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        textView1 = findViewById<View>(R.id.textView1) as TextView

        DownLoadfromCallable(sUrl)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun DownLoadfromCallable(sUrl: String) {

        Observable.fromCallable {
            val sResult = getJsonUrl(sUrl)
            sResult!!
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<String?> {
            override fun onSubscribe(d: @NonNull Disposable?) {

                //Log.e(tag, "DownLoadfromCallable onSubscribe")
            }

            override fun onNext(s: @NonNull String?) {

                //Log.e(tag, "onNext:$s")

                textView1!!.text = s

                try {

                    val resultObj = JSONObject(s)
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
                        //Log.e(tag, opening_price)

                        var closing_price = resultData["closing_price"].toString()
                        if (TextUtils.isEmpty(closing_price)) {
                            closing_price = ""
                        }
                        //Log.e(tag, closing_price)

                        var min_price = resultData["min_price"].toString()
                        if (TextUtils.isEmpty(min_price)) {
                            min_price = ""
                        }
                        //Log.e(tag, min_price)

                        var max_price = resultData["max_price"].toString()
                        if (TextUtils.isEmpty(max_price)) {
                            max_price = ""
                        }
                        //Log.e(tag, max_price)

                    } else {
                        Log.e(tag, "onNext Error")
                    }
                } catch (ex: java.lang.Exception) {
                    ex.printStackTrace()
                }
            }

            override fun onError(e: @NonNull Throwable?) {

                if (e != null) {
                    Log.e(tag, "DownLoadfromCallable onError:" + e.message )
                }
            }

            override fun onComplete() {

                //Log.e(tag, "DownLoadfromCallable onComplete")
            }
        })
    }

    private fun getJsonUrl(sUrl: String): String? {

        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bufferedReader: BufferedReader? = null
        val stringBuilder = StringBuilder()

        try {
            val url = URL(sUrl)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.allowUserInteraction = false
            httpURLConnection!!.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            //httpURLConnection.connectTimeout = 60//타임아웃 시간 설정(default:무한대기)
            //httpURLConnection.setRequestProperty("Content-Type", "application/json")
            httpURLConnection.connect()
            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String? = null
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
            }
            httpURLConnection.disconnect()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return stringBuilder.toString()
    }
}