package com.daeseong.onestore_test

import android.os.AsyncTask
import android.os.Build
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class OneStoreJson : AsyncTask<String?, Void?, String>() {

    companion object {
        private const val tag = "OneStoreJson"
    }

    private var jsonListener: JsonListener? = null

    override fun doInBackground(vararg params: String?): String {
        val sPackageId = params[0]
        val urlText = "http://m.onestore.co.kr/mobilepoc/api/getAppVersion.omp" +
                "?pkgNm=$sPackageId&deviceModelCd=${getDeviceName()}&osVer=${Build.VERSION.RELEASE}"

        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        val stringBuilder = StringBuilder()

        try {
            // SSL https 처리
            HttpsURLConnection.setDefaultHostnameVerifier { _, session -> true }

            val url = URL(urlText)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.useCaches = false
            httpURLConnection.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
            )

            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        stringBuilder.append(line)
                    }
                }
            }
        } catch (e: IOException) {
            Log.e(tag, "Error in HTTP connection", e)
        } finally {
            httpURLConnection?.disconnect()
            inputStream?.close()
        }
        return stringBuilder.toString()
    }

    override fun onPostExecute(sResult: String) {
        jsonListener?.onResult(sResult)
    }

    fun setJsonListener(jsonListener: JsonListener?) {
        this.jsonListener = jsonListener
    }

    interface JsonListener {
        fun onResult(sResult: String?)
    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            model
        } else {
            "$manufacturer $model"
        }
    }
}
