package com.daeseong.threadtask_test.Util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadImageTask(private val imageView: ImageView) : ThreadTask<String?, Bitmap?>() {

    override fun doInBackground(Param: String?): Bitmap? {

        //Log.e("doInBackground:", Param!!)

        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        val bufferedReader: BufferedReader? = null
        var bitmap: Bitmap? = null

        try {

            val url = URL(Param)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.allowUserInteraction = false
            httpURLConnection!!.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connectTimeout = 10000 //타임아웃 시간 설정(default:무한대기)
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

    override fun onPostExecute(Result: Bitmap?) {

        //Log.e("onPostExecute:", Result.toString()!!)

        try {
            if (Result != null) {
                imageView.setImageBitmap(Result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


