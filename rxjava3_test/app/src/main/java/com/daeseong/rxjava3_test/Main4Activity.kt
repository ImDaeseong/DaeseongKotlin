package com.daeseong.rxjava3_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
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
    private val sImgUrl = "https://.png"

    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null

    private var textView1: TextView? = null
    private var textView2: TextView? = null
    private var imageView1: ImageView? = null
    private var imageView2: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        textView1 = findViewById<View>(R.id.textView1) as TextView
        textView2 = findViewById<View>(R.id.textView2) as TextView
        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            try {

                getJson1(sUrl)!!.subscribe(Consumer { result: String? ->
                    //Log.e(tag, "result:$result")
                    textView1!!.text = result
                })
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
            }
        })

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener(View.OnClickListener {

            try {

                getJson2(sUrl)!!.subscribe(Consumer { result: String? ->
                    //Log.e(tag, "result:$result")
                    textView2!!.text = result
                })
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
            }
        })

        button3 = findViewById(R.id.button3)
        button3!!.setOnClickListener(View.OnClickListener {

            try {

                getBitmap1(sImgUrl)!!.onErrorComplete().subscribe(Consumer { result: Bitmap? ->
                    if (result != null)
                        imageView1!!.setImageBitmap(result)
                })
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        })

        button4 = findViewById(R.id.button4)
        button4!!.setOnClickListener(View.OnClickListener {

            try {

                getBitmap2(sImgUrl)!!.onErrorComplete().subscribe(Consumer { result: Bitmap? ->
                    if (result != null)
                        imageView2!!.setImageBitmap(result)
                })
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun getJson1(sUrl: String): @NonNull Observable<String>? {

        return Observable.fromCallable {
            val sResult = getJsonUrl(sUrl)
            sResult!!
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    private fun getJson2(sUrl: String): @NonNull Observable<String>? {

        val callable: Callable<String> = Callable {
            val sResult = getJsonUrl(sUrl)
            sResult!!
        }
        return Observable.fromCallable(callable).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getBitmap1(sUrl: String): @NonNull Observable<Bitmap>? {

        return Observable.fromCallable {
            val bm = getBitmapUrl(sUrl)
            if (bm == null) {
                Log.e(tag, "Bitmap null")
            }
            bm!!
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    private fun getBitmap2(sUrl: String): @NonNull Observable<Bitmap>? {

        val callable: Callable<Bitmap> = Callable {
            val bm = getBitmapUrl(sUrl)
            if (bm == null) {
                Log.e(tag, "Bitmap null")
            }
            bm!!
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

    private fun getBitmapUrl(urlImage: String): Bitmap? {

        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        val bufferedReader: BufferedReader? = null
        var bitmap: Bitmap? = null
        try {
            val url = URL(urlImage)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.allowUserInteraction = false
            httpURLConnection!!.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            //httpURLConnection.connectTimeout = 60//타임아웃 시간 설정(default:무한대기)
            httpURLConnection.connect()
            val resCode = httpURLConnection.responseCode
            if (resCode != HttpURLConnection.HTTP_OK) {
                return null
            }
            inputStream = httpURLConnection.inputStream
            bitmap = BitmapFactory.decodeStream(inputStream)
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
        return bitmap
    }
}