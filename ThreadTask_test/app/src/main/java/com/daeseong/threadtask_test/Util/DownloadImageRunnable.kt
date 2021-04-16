package com.daeseong.threadtask_test.Util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Message
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadImageRunnable(private val sUrl: String, private val handler: Handler) : Runnable {

    override fun run() {

        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        val bufferedReader: BufferedReader? = null
        var bitmap: Bitmap? = null

        try {

            val url = URL(sUrl)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.allowUserInteraction = false
            httpURLConnection!!.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connectTimeout = 10000 //타임아웃 시간 설정(default:무한대기)
            httpURLConnection.connect()

            val resCode = httpURLConnection.responseCode
            if (resCode != HttpURLConnection.HTTP_OK) {
                val message = Message.obtain()
                message.obj = "error"
                message.what = 2
                handler!!.sendMessage(message)
                return
            }
            inputStream = httpURLConnection.inputStream
            bitmap = BitmapFactory.decodeStream(inputStream)
            httpURLConnection.disconnect()

            val message = Message.obtain()
            message.obj = bitmap
            message.what = 1
            handler!!.sendMessage(message)
        } catch (e: IOException) {
            e.printStackTrace()

            val message = Message.obtain()
            message.obj = "error"
            message.what = 2
            handler!!.sendMessage(message)
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
    }
}



