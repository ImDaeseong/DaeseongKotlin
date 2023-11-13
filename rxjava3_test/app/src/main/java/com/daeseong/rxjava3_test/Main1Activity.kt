package com.daeseong.rxjava3_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView

    private val imageUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)

        findViewById<View>(R.id.button1).setOnClickListener {
            downloadFromCallable(imageUrl)
        }

        findViewById<View>(R.id.button2).setOnClickListener {
            downloadCreate(imageUrl)
        }

        findViewById<View>(R.id.button3).setOnClickListener {
            downloadJust(imageUrl)
        }
    }

    private fun downloadFromCallable(url: String) {
        Observable.fromCallable {
            getBitmapFromUrl(url)
        }
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<Bitmap?> {
                override fun onSubscribe(d: Disposable?) {
                    //Log.e(tag, "downloadFromCallable onSubscribe")
                }

                override fun onNext(bitmap: Bitmap?) {
                    imageView1.setImageBitmap(bitmap)
                }

                override fun onError(e: Throwable?) {
                    e?.let {
                        Log.e(tag, "downloadFromCallable onError: ${it.message}")
                    }
                }

                override fun onComplete() {
                    //Log.e(tag, "downloadFromCallable onComplete")
                }
            })
    }

    private fun downloadCreate(url: String) {
        Observable.create<Bitmap> { emitter ->
            val bitmap = getBitmapFromUrl(url)
            emitter.onNext(bitmap)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Bitmap?> {
                override fun onSubscribe(d: Disposable?) {
                    //Log.e(tag, "downloadCreate onSubscribe")
                }

                override fun onNext(bitmap: Bitmap?) {
                    imageView2.setImageBitmap(bitmap)
                }

                override fun onError(e: Throwable?) {
                    e?.let {
                        Log.e(tag, "downloadCreate onError: ${it.message}")
                    }
                }

                override fun onComplete() {
                    //Log.e(tag, "downloadCreate onComplete")
                }
            })
    }

    private fun downloadJust(url: String) {
        Observable.just(url)
            .map { getBitmapFromUrl(url) }
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<Bitmap?> {
                override fun onSubscribe(d: Disposable?) {
                    //Log.e(tag, "downloadJust onSubscribe")
                }

                override fun onNext(bitmap: Bitmap?) {
                    imageView3.setImageBitmap(bitmap)
                }

                override fun onError(e: Throwable?) {
                    e?.let {
                        Log.e(tag, "downloadJust onError: ${it.message}")
                    }
                }

                override fun onComplete() {
                    //Log.e(tag, "downloadJust onComplete")
                }
            })
    }

    private fun getBitmapFromUrl(url: String): Bitmap? {
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bitmap: Bitmap? = null

        try {
            val urlObject = URL(url)
            connection = urlObject.openConnection() as HttpURLConnection
            connection.allowUserInteraction = false
            connection.instanceFollowRedirects = true
            connection.requestMethod = "GET"
            connection.connectTimeout = 60
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.disconnect()
            inputStream?.close()
        }

        return bitmap
    }
}
