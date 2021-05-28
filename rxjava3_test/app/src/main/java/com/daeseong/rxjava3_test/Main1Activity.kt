package com.daeseong.rxjava3_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var imageView1: ImageView? = null
    private var imageView2: ImageView? = null
    private var imageView3: ImageView? = null

    private val sUrl = "https://.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)

        findViewById<View>(R.id.button1).setOnClickListener {

            DownLoadfromCallable(sUrl)
        }

        findViewById<View>(R.id.button2).setOnClickListener {

            DownLoadcreate(sUrl)
        }

        findViewById<View>(R.id.button3).setOnClickListener {

            Downloadjust(sUrl)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun DownLoadfromCallable(sUrl: String) {

        Observable.fromCallable {
            val bm = getBitmapUrl(sUrl)
            bm!!
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<Bitmap?> {
            override fun onSubscribe(d: @NonNull Disposable?) {

                //Log.e(tag, "DownLoadfromCallable onSubscribe")
            }

            override fun onNext(bitmap: @NonNull Bitmap?) {

                //Log.e(tag, "DownLoadfromCallable onNext")

                if (bitmap != null)
                    imageView1!!.setImageBitmap(bitmap)
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

    private fun DownLoadcreate(sUrl: String) {

        Observable.create<Bitmap> { emitter ->
            val bm = getBitmapUrl(sUrl)
            emitter!!.onNext(bm)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<Bitmap?> {
            override fun onSubscribe(d: @NonNull Disposable?) {

                //Log.e(tag, "DownLoadcreate onSubscribe")
            }

            override fun onNext(bitmap: @NonNull Bitmap?) {

                //Log.e(tag, "DownLoadcreate onNext")

                if (bitmap != null)
                    imageView2!!.setImageBitmap(bitmap)
            }

            override fun onError(e: @NonNull Throwable?) {

                if (e != null) {
                    Log.e(tag, "DownLoadcreate onError:" + e.message)
                }
            }

            override fun onComplete() {

                //Log.e(tag, "DownLoadcreate onComplete")
            }
        })
    }

    private fun Downloadjust(sUrl: String) {

        Observable.just(sUrl)
        .map { getBitmapUrl(sUrl) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<Bitmap?> {
            override fun onSubscribe(d: @NonNull Disposable?) {

                //Log.e(tag, "Downloadjust onSubscribe")
            }

            override fun onNext(bitmap: @NonNull Bitmap?) {

                //Log.e(tag, "Downloadjust onNext")

                if (bitmap != null)
                    imageView3!!.setImageBitmap(bitmap)
            }

            override fun onError(e: @NonNull Throwable?) {

                if (e != null) {
                    Log.e(tag, "Downloadjust onError:" + e.message)
                }
            }

            override fun onComplete() {

                //Log.e(tag, "Downloadjust onComplete")
            }
        })
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
            httpURLConnection.connectTimeout = 60//타임아웃 시간 설정(default:무한대기)
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