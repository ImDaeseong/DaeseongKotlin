package com.daeseong.paging_test.Common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object HttpUtil {

    fun GetDataString(sUrl: String?): String {

        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bufferedReader: BufferedReader? = null
        val stringBuilder = StringBuilder()

        try {

            //SSL https 처리
            HttpsURLConnection.setDefaultHostnameVerifier { hostname, session ->
                hostname == session.peerHost
            }

            val url = URL(sUrl)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection!!.allowUserInteraction = false
            httpURLConnection.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            //httpURLConnection.connectTimeout = 60//타임아웃 시간 설정(default:무한대기)
            httpURLConnection.connect()

            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {

                inputStream = httpURLConnection.inputStream
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String? = null
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
            }
            httpURLConnection.disconnect()

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (e: IOException) {
                }
            }
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect()
                } catch (e: Exception) {
                }
            }
        }
        return stringBuilder.toString()
    }

    fun GetDataBitmap(sUrl: String): Bitmap? {

        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        val bufferedReader: BufferedReader? = null
        var bitmap: Bitmap? = null

        try {

            //SSL https 처리
            HttpsURLConnection.setDefaultHostnameVerifier { hostname, session ->
                hostname == session.peerHost
            }

            val url = URL(sUrl)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection!!.allowUserInteraction = false
            httpURLConnection.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            //httpURLConnection.connectTimeout = 60//타임아웃 시간 설정(default:무한대기)
            httpURLConnection.connect()

            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
            }
            httpURLConnection.disconnect()

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (e: IOException) {
                }
            }
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect()
                } catch (e: Exception) {
                }
            }
        }
        return bitmap
    }
}
