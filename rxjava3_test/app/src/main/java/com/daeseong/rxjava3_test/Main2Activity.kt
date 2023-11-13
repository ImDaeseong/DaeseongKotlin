package com.daeseong.rxjava3_test

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
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
    private lateinit var textView1: TextView

    private val apiUrl = "https://api.bithumb.com/public/ticker/BTC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        textView1 = findViewById(R.id.textView1)

        downloadFromCallable(apiUrl)
    }

    private fun downloadFromCallable(apiUrl: String) {
        Observable.fromCallable {
            getJsonUrl(apiUrl)
        }
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<String?> {
                override fun onSubscribe(d: @NonNull Disposable?) {
                    //Log.e(tag, "downloadFromCallable onSubscribe")
                }

                override fun onNext(s: @NonNull String?) {
                    handleJsonResult(s)
                }

                override fun onError(e: @NonNull Throwable?) {
                    e?.let {
                        Log.e(tag, "downloadFromCallable onError:${it.message}")
                    }
                }

                override fun onComplete() {
                    //Log.e(tag, "downloadFromCallable onComplete")
                }
            })
    }

    private fun getJsonUrl(apiUrl: String): String? {
        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bufferedReader: BufferedReader? = null
        val stringBuilder = StringBuilder()

        try {
            val url = URL(apiUrl)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.allowUserInteraction = false
            httpURLConnection.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()
            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            httpURLConnection?.disconnect()
            inputStream?.close()
            bufferedReader?.close()
        }

        return stringBuilder.toString()
    }

    private fun handleJsonResult(jsonString: String?) {
        try {
            val resultObj = JSONObject(jsonString.orEmpty())
            val status = resultObj.optString("status", "")
            val data = resultObj.optString("data", "")

            if (status == "0000") {
                val resultData = JSONObject(data)
                val openingPrice = resultData.optString("opening_price", "")
                val closingPrice = resultData.optString("closing_price", "")
                val minPrice = resultData.optString("min_price", "")
                val maxPrice = resultData.optString("max_price", "")

                //Log.e(tag, openingPrice)
                //Log.e(tag, closingPrice)
                //Log.e(tag, minPrice)
                //Log.e(tag, maxPrice)

                textView1.text = "Opening Price: $openingPrice\nClosing Price: $closingPrice\nMin Price: $minPrice\nMax Price: $maxPrice"
            } else {
                Log.e(tag, "handleJsonResult Error")
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
