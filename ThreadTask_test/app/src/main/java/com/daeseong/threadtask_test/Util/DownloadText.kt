package com.daeseong.threadtask_test.Util

import android.os.Handler
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadText(private val sUrl: String, private val handler: Handler) : Runnable {

    private var listen: onDownloadTextListen? = null
    fun setonDownloadTextListen(listen: onDownloadTextListen?) {
        this.listen = listen
    }

    private var sText: String? = null

    fun start() {
        Thread(this).start()
    }

    fun getText(): String? {
        return this.sText
    }

    override fun run() {

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
            httpURLConnection.connectTimeout = 10000 //타임아웃 시간 설정(default:무한대기)
            httpURLConnection.connect()

            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String? = null
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                    stringBuilder.append("\n")
                }
            }
            httpURLConnection.disconnect()

            sText = stringBuilder.toString()

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

    interface onDownloadTextListen {
        fun onCompleted(downloadText: DownloadText?)
        fun onError(downloadText: DownloadText?, sMessage: String?)
    }
}
