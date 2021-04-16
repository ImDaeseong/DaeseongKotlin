package com.daeseong.threadtask_test.Util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadRunnabe(private val sUrl: String) : Runnable {

    private var bitmap: Bitmap? = null

    private var handler: Handler? = null
    private var listen: onDownloadRunnabeListen? = null

    fun start(handler: Handler?, listener: onDownloadRunnabeListen?) {
        this.handler = handler
        this.listen = listener
        Thread(this).start()
    }

    fun getBitmap(): Bitmap? {
        return bitmap
    }

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
                handler!!.post {
                    if (listen != null) {
                        listen!!.onError(this, "error")
                    }
                }
                return
            }
            inputStream = httpURLConnection.inputStream
            bitmap = BitmapFactory.decodeStream(inputStream)
            httpURLConnection.disconnect()

            this.bitmap = bitmap

            handler!!.post {
                if (listen != null) {
                    listen!!.onCompleted(this)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            handler!!.post {
                if (listen != null) {
                    listen!!.onError(this, "error")
                }
            }
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

    interface onDownloadRunnabeListen {
        fun onCompleted(downloadRunnabe: DownloadRunnabe?)
        fun onError(downloadRunnabe: DownloadRunnabe?, sMessage: String?)
    }
}
