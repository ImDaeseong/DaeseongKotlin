package com.daeseong.http_test.HttpUtil

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.daeseong.http_test.ImageView2Activity
import java.io.IOException
import java.io.InputStream
import java.net.URL


class DownloadImage1(private val imageView2Activity: ImageView2Activity) :  AsyncTask<String?, Void?, Bitmap?>() {

    override fun doInBackground(vararg params: String?): Bitmap? {

        var inputStream: InputStream? = null
        var bitmap: Bitmap? = null

        try {

            val url = URL(params[0])
            inputStream = url.openStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
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
        }
        return bitmap
    }

    override fun onPostExecute(bitmap: Bitmap?) {
        imageView2Activity.ImageViewBitmap(bitmap)
    }
}