package com.daeseong.http_test.HttpUtil

import android.app.ProgressDialog
import android.os.AsyncTask
import com.daeseong.http_test.ImageTextView2Activity
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class DownloadJson1(private val imageTextView2Activity: ImageTextView2Activity) : AsyncTask<Void?, Void?, String>() {

    var url1 = "https://"

    private val progressDialog: ProgressDialog = ProgressDialog.show(imageTextView2Activity, "접속중...", "이미지 다운로드중...", true)

    override fun doInBackground(vararg params: Void?): String? {

        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bufferedReader: BufferedReader? = null
        val stringBuilder = StringBuilder()

        try {

            val url = URL(url1)
            httpURLConnection = url.openConnection() as HttpURLConnection
            inputStream = BufferedInputStream(httpURLConnection.inputStream)
            bufferedReader = BufferedReader(InputStreamReader(inputStream))

            var line: String? = ""
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            httpURLConnection.disconnect()
        } catch (e: Exception) {
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

    override fun onPostExecute(s: String) {
        super.onPostExecute(s)

        progressDialog.dismiss()
        imageTextView2Activity.LoadjsonData(s)
    }

}