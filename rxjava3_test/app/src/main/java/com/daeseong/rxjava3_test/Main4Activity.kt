package com.daeseong.rxjava3_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable

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
        button1.setOnClickListener {
            getJson1(sUrl)?.subscribeBy { result ->
                //println("result:$result")
                textView1.text = result
            }
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            getJson2(sUrl)?.subscribeBy { result ->
                //println("result:$result")
                textView2.text = result
            }
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            getBitmap1(sImgUrl)?.onErrorComplete()?.subscribeBy { result ->
                result?.let { imageView1.setImageBitmap(it) }
            }
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            getBitmap2(sImgUrl)?.onErrorComplete()?.subscribeBy { result ->
                result?.let { imageView2.setImageBitmap(it) }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun getJson1(sUrl: String): Observable<String>? {
        return Observable.fromCallable {
            getJsonUrl(sUrl) ?: ""
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    private fun getJson2(sUrl: String): Observable<String>? {
        val callable: Callable<String> = Callable {
            getJsonUrl(sUrl) ?: ""
        }
        return Observable.fromCallable(callable).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getBitmap1(sUrl: String): Observable<Bitmap>? {
        return Observable.fromCallable {
            getBitmapUrl(sUrl) ?: throw IOException("Bitmap null")
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    private fun getBitmap2(sUrl: String): Observable<Bitmap>? {
        val callable: Callable<Bitmap> = Callable {
            getBitmapUrl(sUrl) ?: throw IOException("Bitmap null")
        }
        return Observable.fromCallable(callable).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
            bufferedReader?.close()
            httpURLConnection?.disconnect()
        }
        return stringBuilder.toString()
    }

    private fun getBitmapUrl(urlImage: String): Bitmap? {
        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bitmap: Bitmap? = null
        try {
            val url = URL(urlImage)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.allowUserInteraction = false
            httpURLConnection!!.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()
            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
            httpURLConnection?.disconnect()
        }
        return bitmap
    }
}
