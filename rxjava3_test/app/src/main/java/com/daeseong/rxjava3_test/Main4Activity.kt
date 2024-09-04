package com.daeseong.rxjava3_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Main4Activity : AppCompatActivity() {

    private val tag = Main4Activity::class.java.simpleName

    private val sUrl = "https://api.bithumb.com/public/ticker/BTC"
    private val sImgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener {
            fetchJson(sUrl, textView1)
        }

        button2.setOnClickListener {
            fetchJson(sUrl, textView2)
        }

        button3.setOnClickListener {
            fetchBitmap(sImgUrl, imageView1)
        }

        button4.setOnClickListener {
            fetchBitmap(sImgUrl, imageView2)
        }
    }

    private fun fetchJson(url: String, textView: TextView) {
        getJsonObservable(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { result ->
                    textView.text = result
                },
                onError = { throwable ->
                    Log.e(tag, "Error:", throwable)
                }
            )
    }

    private fun fetchBitmap(url: String, imageView: ImageView) {
        getBitmapObservable(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { bitmap ->
                    imageView.setImageBitmap(bitmap)
                },
                onError = { throwable ->
                    Log.e(tag, "Error:", throwable)
                }
            )
    }

    private fun getJsonObservable(url: String): Single<String> {
        return Single.fromCallable {
            fetchJsonFromUrl(url) ?: ""
        }
    }

    private fun getBitmapObservable(url: String): Single<Bitmap> {
        return Single.fromCallable {
            fetchBitmapFromUrl(url) ?: throw IOException("Bitmap is null")
        }
    }

    private fun fetchJsonFromUrl(url: String): String? {
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var reader: BufferedReader? = null
        val result = StringBuilder()

        try {
            val urlObj = URL(url)
            connection = urlObj.openConnection() as HttpURLConnection
            connection.apply {
                requestMethod = "GET"
                connect()
            }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.inputStream
                reader = BufferedReader(InputStreamReader(inputStream))
                reader.useLines { lines ->
                    lines.forEach { line ->
                        result.append(line)
                    }
                }
            }
        } catch (e: IOException) {
            Log.e(tag, "Error:", e)
        } finally {
            inputStream?.close()
            reader?.close()
            connection?.disconnect()
        }
        return result.toString()
    }

    private fun fetchBitmapFromUrl(url: String): Bitmap? {
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bitmap: Bitmap? = null

        try {
            val urlObj = URL(url)
            connection = urlObj.openConnection() as HttpURLConnection
            connection.apply {
                requestMethod = "GET"
                connect()
            }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: IOException) {
            Log.e(tag, "Error:", e)
        } finally {
            inputStream?.close()
            connection?.disconnect()
        }
        return bitmap
    }
}
