package com.daeseong.banner_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Banner1Activity : AppCompatActivity() {

    private val tag = Banner1Activity::class.java.simpleName

    private var button1: Button? = null
    private var imageView1: ImageView? = null
    private var imageView2:ImageView? = null
    private var imageView3:ImageView? = null
    private var imageView4:ImageView? = null
    private var imageView5:ImageView? = null

    val url1 = "https://.png"
    val url2 = "https://.png"
    val url3 = "https://.png"
    val url4 = "https://.png"
    val url5 = "https://.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner1)

        button1 = findViewById<Button>(R.id.button1);
        imageView1 = findViewById<ImageView>(R.id.imageView1)
        imageView2 = findViewById<ImageView>(R.id.imageView2)
        imageView3 = findViewById<ImageView>(R.id.imageView3)
        imageView4 = findViewById<ImageView>(R.id.imageView4)
        imageView5 = findViewById<ImageView>(R.id.imageView5)

        button1!!.setOnClickListener {

            DownloadImage().execute(url1, url2, url3, url4, url5)
        }
    }

    inner class DownloadImage : AsyncTask<String?, Int?, List<Bitmap?>?>() {

        override fun doInBackground(vararg params: String?): List<Bitmap?>? {

            val urlCount = params.size
            var httpURLConnection: HttpURLConnection? = null
            var inputStream: InputStream? = null
            val bufferedReader: BufferedReader? = null
            var bitmap: Bitmap? = null
            val bitmaps: MutableList<Bitmap?> = ArrayList()

            for (i in 0 until urlCount) {

                if (isCancelled) break

                try {

                    val url = URL(params[i])

                    httpURLConnection = url.openConnection() as HttpURLConnection
                    httpURLConnection.allowUserInteraction = false
                    httpURLConnection.instanceFollowRedirects = true
                    httpURLConnection.requestMethod = "GET"
                    httpURLConnection.connect()

                    val resCode = httpURLConnection.responseCode
                    if (resCode != HttpURLConnection.HTTP_OK) {
                        return null
                    }

                    inputStream = httpURLConnection.inputStream
                    bitmap = BitmapFactory.decodeStream(inputStream)
                    bitmaps.add(bitmap)
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
            }
            return bitmaps
        }

        override fun onPostExecute(bitmaps: List<Bitmap?>?) {

            try {

                //Log.e(tag, "DownloadImage onPostExecute")

                for (i in bitmaps!!.indices) {

                    val bitmap = bitmaps[i]
                    if (bitmap == null) {
                        Log.e(tag, "bitmap null:$i")
                    }

                    when (i) {
                        0 -> {
                            imageView1!!.setImageBitmap(bitmap)
                        }
                        1 -> {
                            imageView2!!.setImageBitmap(bitmap)
                        }
                        2 -> {
                            imageView3!!.setImageBitmap(bitmap)
                        }
                        3 -> {
                            imageView4!!.setImageBitmap(bitmap)
                        }
                        4 -> {
                            imageView5!!.setImageBitmap(bitmap)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()

            //Log.e(tag, "DownloadImage onPreExecute")
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)

            //Log.e(tag, "DownloadImage onProgressUpdate:" + values[0].toString())
        }

        override fun onCancelled() {
            super.onCancelled()

            //Log.e(tag, "DownloadImage onCancelled")
        }
    }

}
