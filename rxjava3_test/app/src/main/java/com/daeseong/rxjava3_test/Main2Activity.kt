package com.daeseong.rxjava3_test

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.json.JSONObject
import java.io.IOException
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
        Single.fromCallable { getJsonUrl(apiUrl) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { jsonString ->
                    handleJsonResult(jsonString)
                },
                onError = { throwable ->
                    Log.e(tag, "Error: ${throwable.message}")
                }
            )
    }

    private fun getJsonUrl(apiUrl: String): String {
        val stringBuilder = StringBuilder()

        try {

            val url = URL(apiUrl)
            with(url.openConnection() as HttpURLConnection) {
                allowUserInteraction = false
                instanceFollowRedirects = true
                requestMethod = "GET"
                connect()

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream.bufferedReader().use { reader ->
                        reader.forEachLine { line ->
                            stringBuilder.append(line)
                        }
                    }
                } else {
                    Log.e(tag, "error: $responseCode")
                }
            }
        } catch (e: IOException) {
            Log.e(tag, "IOException: ${e.message}")
        }

        return stringBuilder.toString()
    }

    private fun handleJsonResult(jsonString: String) {
        try {
            val resultObj = JSONObject(jsonString)
            val status = resultObj.optString("status", "")
            val data = resultObj.optString("data", "")

            if (status == "0000") {
                val resultData = JSONObject(data)
                val openingPrice = resultData.optString("opening_price", "N/A")
                val closingPrice = resultData.optString("closing_price", "N/A")
                val minPrice = resultData.optString("min_price", "N/A")
                val maxPrice = resultData.optString("max_price", "N/A")

                textView1.text = """
                    Opening Price: $openingPrice
                    Closing Price: $closingPrice
                    Min Price: $minPrice
                    Max Price: $maxPrice
                """.trimIndent()

            } else {
                Log.e(tag, "Error: Invalid status")
            }

        } catch (ex: Exception) {
            Log.e(tag, "Exception: ${ex.message}")
        }
    }
}
