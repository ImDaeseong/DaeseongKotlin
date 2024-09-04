package com.daeseong.rxjava3_test.Common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadUtil {

    fun getData(sUrl: String): Single<String> {
        return Single.fromCallable {
            try {
                getJsonUrl(sUrl)
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }
    }

    fun getBitmap(sUrl: String): Maybe<Bitmap> {
        return Maybe.fromCallable {
            try {
                getBitmapUrl(sUrl)?.let { bitmap ->
                    bitmap
                } ?: throw NoSuchElementException("Bitmap is null")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun getJsonUrl(sUrl: String): String {
        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bufferedReader: BufferedReader? = null
        val stringBuilder = StringBuilder()

        try {
            val url = URL(sUrl)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.apply {
                allowUserInteraction = false
                instanceFollowRedirects = true
                requestMethod = "GET"
                connect()
            }

            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                bufferedReader.use { reader ->
                    reader.forEachLine { line ->
                        stringBuilder.append(line)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
            bufferedReader?.close()
            httpURLConnection?.disconnect()
        }

        return stringBuilder.toString()
    }

    private fun getBitmapUrl(urlImage: String): Bitmap? {
        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bitmap: Bitmap? = null

        try {
            val url = URL(urlImage)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.apply {
                allowUserInteraction = false
                instanceFollowRedirects = true
                requestMethod = "GET"
                connect()
            }

            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
            httpURLConnection?.disconnect()
        }

        return bitmap
    }
}
