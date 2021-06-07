package com.daeseong.rxjava3_test.Common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Observable
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadJson {

    fun getData(sUrl: String): Observable<String> {
        return Observable.fromCallable {
            var sResult = ""
            try {
                sResult = getJsonUrl(sUrl)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            sResult
        }
    }

    fun getBitmap(sUrl: String?): Observable<Bitmap?>? {
        return Observable.fromCallable {
            var bm: Bitmap? = null
            try {
                bm = getBitmapUrl(sUrl!!)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            bm!!
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
            httpURLConnection.allowUserInteraction = false
            httpURLConnection!!.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            //httpURLConnection.connectTimeout = 60//타임아웃 시간 설정(default:무한대기)
            //httpURLConnection.setRequestProperty("Content-Type", "application/json")
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
        return stringBuilder.toString()
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
            //httpURLConnection.connectTimeout = 60//타임아웃 시간 설정(default:무한대기)
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
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }
        return bitmap
    }
}