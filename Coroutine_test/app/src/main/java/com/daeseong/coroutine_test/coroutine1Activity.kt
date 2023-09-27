package com.daeseong.coroutine_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class coroutine1Activity : AppCompatActivity() {

    private val tag: String = coroutine1Activity::class.java.simpleName

    private lateinit var imageView1: ImageView
    private val sUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine1)

        imageView1 = findViewById(R.id.imageView1)

        GlobalScope.launch(Dispatchers.IO) {

            try {
                val bitmap = getBitmapFromUrl(sUrl)
                updateImageView(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private suspend fun getBitmapFromUrl(urlImage: String): Bitmap? {

        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null

        return try {
            val url = URL(urlImage)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.allowUserInteraction = false
            httpURLConnection.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connectTimeout = 60000 // 타임아웃 시간 설정 (60초)
            httpURLConnection.connect()

            if (httpURLConnection.responseCode != HttpURLConnection.HTTP_OK) {
                null
            } else {
                inputStream = httpURLConnection.inputStream
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: IOException) {
            null
        } finally {
            inputStream?.close()
            httpURLConnection?.disconnect()
        }
    }

    private suspend fun updateImageView(bitmap: Bitmap?) {
        withContext(Dispatchers.Main) {
            imageView1.setImageBitmap(bitmap)
        }
    }
}