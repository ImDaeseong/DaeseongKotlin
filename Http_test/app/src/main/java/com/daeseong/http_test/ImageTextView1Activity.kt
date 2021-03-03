package com.daeseong.http_test

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class ImageTextView1Activity : AppCompatActivity() {

    private val tag: String = ImageTextView1Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_text_view1)

        try {
            if (IsConnection()) {
                LoadData()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun LoadData() {

        object : AsyncTask<Void?, Void?, String?>() {

            override fun doInBackground(vararg params: Void?): String? {
                val url1 = "https://"
                return GetUrlData(url1)
            }

            override fun onPostExecute(s: String?) {

                Log.e(tag, "LoadData:$s")
            }

        }.execute()
    }

    fun GetUrlData(sUrl: String?): String? {

        val sData = StringBuilder()

        try {

            val url = URL(sUrl)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

            if (connection != null) {
                connection.connectTimeout = 10000
                connection.useCaches = false

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {

                    val isr = InputStreamReader(connection.inputStream)
                    val br = BufferedReader(isr)
                    var line = ""
                    while (br.readLine().also { line = it } != null) {
                        sData.append("""$line""".trimIndent())
                    }
                    br.close()
                    isr.close()
                }
                connection.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return sData.toString()
    }

    fun IsConnection(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo ?: return false
        if (!networkInfo.isConnected) {
            return false
        }
        return networkInfo.isAvailable
    }
}
