package com.daeseong.onestore_test

import android.os.AsyncTask
import android.os.Build
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class OneStoreJson : AsyncTask<String?, Void?, String>() {

    companion object {
        private val tag = OneStoreJson::class.java.simpleName
    }

    private var jsonListener: JsonListener? = null

    override fun doInBackground(vararg params: String?): String {

        val sPackageId = params[0]
        val urlText = String.format(
            "http://m.onestore.co.kr/mobilepoc/api/getAppVersion.omp?pkgNm=%s&deviceModelCd=%s&osVer=%s",
            sPackageId,
            getDeviceName(),
            Build.VERSION.RELEASE
        )

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
            httpURLConnection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
            )

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

    private fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            capitalize(model)
        } else {
            capitalize(manufacturer) + " " + model
        }
    }

    private fun capitalize(s: String?): String {
        if (s == null || s.isEmpty()) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            Character.toUpperCase(first).toString() + s.substring(1)
        }
    }

}
