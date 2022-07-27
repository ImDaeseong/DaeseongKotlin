package com.daeseong.coroutine_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class coroutine1Activity : AppCompatActivity() {

    private val tag: String = coroutine1Activity::class.java.simpleName

    private var imageView1: ImageView? = null

    private val sUrl = "https://.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine1)

        imageView1 = findViewById<ImageView>(R.id.imageView1)

        GlobalScopeImageView()
    }

    private fun GlobalScopeImageView(){

        GlobalScope.launch(Dispatchers.IO) {

            //백그라운드 처리

            var bitmap: Bitmap? = null
            try {

                bitmap = getBitmapUrl(sUrl)

                GlobalScope.launch(Dispatchers.Main) {

                    //UI 처리
                    imageView1!!.setImageBitmap(bitmap);
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
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