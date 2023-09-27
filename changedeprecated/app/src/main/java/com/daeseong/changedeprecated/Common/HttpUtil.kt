package com.daeseong.changedeprecated.Common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object HttpUtil {

    fun getDataString(sUrl: String?): String {
        return try {
            val url = URL(sUrl)
            val httpURLConnection = url.openConnection() as HttpURLConnection

            // SSL https 처리
            HttpsURLConnection.setDefaultHostnameVerifier { hostname, session ->
                hostname == session.peerHost
            }

            httpURLConnection.allowUserInteraction = false
            httpURLConnection.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()

            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                val inputStream = httpURLConnection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                val stringBuilder = StringBuilder()
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
                inputStream.close()
                stringBuilder.toString()
            } else {
                ""
            }
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

    fun getDataBitmap(sUrl: String): Bitmap? {
        return try {
            val url = URL(sUrl)
            val httpURLConnection = url.openConnection() as HttpURLConnection

            // SSL https 처리
            HttpsURLConnection.setDefaultHostnameVerifier { hostname, session ->
                hostname == session.peerHost
            }

            httpURLConnection.allowUserInteraction = false
            httpURLConnection.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()

            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                val inputStream = httpURLConnection.inputStream
                BitmapFactory.decodeStream(inputStream)
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
