package com.daeseong.threadtask_test.Util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadManager {

    companion object {
        private var handler: Handler? = null
        private var instance: DownloadManager? = null
        fun getInstance(): DownloadManager {
            if (instance == null) {
                instance = DownloadManager()
                handler = Handler(Looper.getMainLooper())
            }
            return instance as DownloadManager
        }
    }

    interface onCompleteListen {
        fun onCompleted(bitmap: Bitmap?)
        fun onError(sMessage: String?)
    }

    fun getImage(sUrl: String?, listen: onCompleteListen?) {
        Thread(DownloadRunnable(sUrl!!, listen)).start()
    }

    inner class DownloadRunnable(private val sUrl: String, private val listen: onCompleteListen?) : Runnable {

        override fun run() {

            var httpURLConnection: HttpURLConnection? = null
            var inputStream: InputStream? = null
            val bufferedReader: BufferedReader? = null

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
                    handler!!.post(Runnable {
                        listen?.onError("error")
                    })
                    return
                }
                inputStream = httpURLConnection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)
                httpURLConnection.disconnect()

                handler!!.post(Runnable {
                    listen?.onCompleted(bitmap)
                })
            } catch (e: IOException) {
                e.printStackTrace()
                handler!!.post(Runnable {
                    listen?.onError("error")
                })
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

}


