package com.daeseong.http_test.HttpUtil

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.widget.TextView
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class SendMessage(context: Context, textView: TextView) : AsyncTask<String?, Void?, String?>() {

    private val tag: String = SendMessage::class.java.simpleName

    private val context: Context = context
    private val connectivityManager: ConnectivityManager
    private val textView: TextView = textView

    init {
        connectivityManager = this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun doInBackground(vararg params: String?): String? {

        val urlText = params[0]
        val urlParameters = params[1]
        var httpURLConnection: HttpURLConnection? = null
        var dataOutputStream: DataOutputStream? = null
        var bufferedReader: BufferedReader? = null
        val stringBuilder = StringBuilder()

        try {

            val networkInfo = connectivityManager.allNetworkInfo
            for (info in networkInfo) {

                if (info.state == NetworkInfo.State.CONNECTED) {

                    val postData = urlParameters!!.toByteArray(charset("UTF-8"))
                    val postDataLength = postData.size
                    val url = URL(urlText)

                    httpURLConnection = url.openConnection() as HttpURLConnection
                    httpURLConnection.requestMethod = "POST"
                    httpURLConnection.doInput = true
                    httpURLConnection.doOutput = true
                    httpURLConnection.instanceFollowRedirects = false
                    httpURLConnection.setRequestProperty(
                        "Content-Type",
                        "application/x-www-form-urlencoded"
                    )

                    httpURLConnection.setRequestProperty("charset", "utf-8")
                    httpURLConnection.setRequestProperty(
                        "Content-Length",
                        postDataLength.toString()
                    )

                    httpURLConnection.useCaches = false
                    httpURLConnection.connect()
                    dataOutputStream = DataOutputStream(httpURLConnection.outputStream)
                    dataOutputStream.write(postData)
                    dataOutputStream.flush()
                    dataOutputStream.close()
                    val resCode: Int = httpURLConnection.responseCode

                    bufferedReader = if (resCode == 200) {
                        BufferedReader(
                            InputStreamReader(
                                httpURLConnection.inputStream,
                                "UTF-8"
                            )
                        )
                    } else {
                        BufferedReader(
                            InputStreamReader(
                                httpURLConnection.inputStream,
                                "UTF-8"
                            )
                        )
                    }

                    var line: String? = ""
                    while (bufferedReader.readLine().also { line = it } != null) {
                        stringBuilder.append(line)
                    }
                    httpURLConnection.disconnect()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {

            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close()
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

    override fun onPostExecute(s: String?) {
        super.onPostExecute(s)

        try {

            if (s != null) {
                textView.text = s.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}