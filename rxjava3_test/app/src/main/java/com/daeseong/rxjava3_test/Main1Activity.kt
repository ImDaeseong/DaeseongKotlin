package com.daeseong.rxjava3_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView

    private val imageUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)

        findViewById<Button>(R.id.button1).setOnClickListener {
            downloadFromCallable(imageUrl)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            downloadCreate(imageUrl)
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            downloadJust(imageUrl)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun downloadFromCallable(url: String) {

        val disposable = Observable.fromCallable { getBitmapFromUrl(url) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { bitmap ->
                    imageView1.setImageBitmap(bitmap) // Bitmap이 null일 경우에도 처리
                },
                { error ->
                    Log.e(tag, "downloadFromCallable error: ${error.message}", error)
                    imageView1.setImageResource(R.drawable.ic_launcher_background)
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun downloadCreate(url: String) {

        val disposable = Observable.create<Bitmap> { emitter ->
            try {
                val bitmap = getBitmapFromUrl(url)
                emitter.onNext(bitmap) // Bitmap이 null일 경우에도 처리
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { bitmap -> imageView2.setImageBitmap(bitmap) },
                { error ->
                    Log.e(tag, "downloadCreate error: ${error.message}", error)
                    imageView2.setImageResource(R.drawable.ic_launcher_background)
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun downloadJust(url: String) {

        val disposable = Observable.just(url)
            .map { getBitmapFromUrl(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { bitmap -> imageView3.setImageBitmap(bitmap) },
                { error ->
                    Log.e(tag, "downloadJust error: ${error.message}", error)
                    imageView3.setImageResource(R.drawable.ic_launcher_background)
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun getBitmapFromUrl(url: String): Bitmap {

        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null

        return try {
            val urlObject = URL(url)
            connection = urlObject.openConnection() as HttpURLConnection
            connection.apply {
                allowUserInteraction = false
                instanceFollowRedirects = true
                requestMethod = "GET"
                connectTimeout = 60000 //60초
                connect()
            }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.inputStream
                BitmapFactory.decodeStream(inputStream) ?: throw IOException("Failed to decode bitmap")
            } else {
                throw IOException("error: ${connection.responseCode}")
            }
        } catch (e: Exception) {
            throw e
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
                Log.e(tag, "Error: ${e.message}", e)
            }
            connection?.disconnect()
        }
    }
}
