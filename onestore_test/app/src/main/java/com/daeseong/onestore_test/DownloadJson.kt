package com.daeseong.onestore_test

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DownloadJson : AsyncTask<String?, Void?, String>() {

    private var jsonListener: JsonListener? = null

    override fun doInBackground(vararg params: String?): String {

        val urlText = params[0]
        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bufferedReader: BufferedReader? = null
        val stringBuilder = StringBuilder()

        try {

            //SSL https 처리
            HttpsURLConnection.setDefaultHostnameVerifier { hostname, session ->
                hostname == session.peerHost
            }

            val url = URL(urlText)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection!!.allowUserInteraction = false
            httpURLConnection!!.instanceFollowRedirects = true
            httpURLConnection!!.requestMethod = "GET"
            httpURLConnection!!.connect()
            val resCode = httpURLConnection!!.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection!!.inputStream
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String? = null
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
            }
            httpURLConnection!!.disconnect()
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

    override fun onPostExecute(sResult: String) {
        if (jsonListener != null) {
            jsonListener!!.onResult(sResult)
        }
    }

    fun setJsonListener(jsonListener: JsonListener?) {
        this.jsonListener = jsonListener
    }

    interface JsonListener {
        fun onResult(sResult: String?)
    }
}