package com.daeseong.http_test.HttpUtil

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.daeseong.http_test.ImageTextView2Activity
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class DownloadImage2(imageTextView2Activity: ImageTextView2Activity) : AsyncTask<Void?, Void?, Bitmap?>() {

    var url1 = "https://.png"

    private val imageTextView2Activity: ImageTextView2Activity = imageTextView2Activity
    private val progressDialog: ProgressDialog = ProgressDialog.show(imageTextView2Activity, "접속중...", "이미지 다운로드중...", true)

    override fun doInBackground(vararg params: Void?): Bitmap? {

        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bitmap: Bitmap? = null

        try {

            val url = URL(url1)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.doInput = true
            httpURLConnection.connect()
            inputStream = httpURLConnection.inputStream
            bitmap = BitmapFactory.decodeStream(inputStream)
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

            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return bitmap
    }

    override fun onPostExecute(bitmap: Bitmap?) {
        super.onPostExecute(bitmap)

        progressDialog.dismiss()
        imageTextView2Activity.ImageViewBitmap(bitmap)
    }
}