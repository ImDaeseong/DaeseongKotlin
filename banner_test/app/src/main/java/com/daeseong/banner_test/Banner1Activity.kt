package com.daeseong.banner_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Banner1Activity : AppCompatActivity() {

    private val tag = Banner1Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var imageViewList: List<ImageView>

    private val urls = listOf(
        "https://.png",
        "https://.png",
        "https://.png",
        "https://.png",
        "https://.png"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner1)

        button1 = findViewById(R.id.button1)

        imageViewList = listOf(
            findViewById(R.id.imageView1),
            findViewById(R.id.imageView2),
            findViewById(R.id.imageView3),
            findViewById(R.id.imageView4),
            findViewById(R.id.imageView5)
        )

        button1.setOnClickListener {
            DownloadImageTask().execute(*urls.toTypedArray())
        }
    }

    inner class DownloadImageTask : AsyncTask<String, Int, List<Bitmap>>() {

        override fun doInBackground(vararg params: String): List<Bitmap> {
            val bitmaps = mutableListOf<Bitmap>()

            for (url in params) {
                if (isCancelled) break

                try {
                    val imageUrl = URL(url)
                    val connection = imageUrl.openConnection() as HttpURLConnection
                    connection.allowUserInteraction = false
                    connection.instanceFollowRedirects = true
                    connection.requestMethod = "GET"
                    connection.connect()

                    val responseCode = connection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        val inputStream: InputStream = connection.inputStream
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        bitmaps.add(bitmap)
                    } else {
                        // Handle error here
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            return bitmaps
        }

        override fun onPostExecute(bitmaps: List<Bitmap>) {
            try {
                for (i in bitmaps.indices) {
                    val bitmap = bitmaps[i]
                    imageViewList.getOrNull(i)?.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()
            // Pre-execution setup, if needed
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            // Update progress, if needed
        }

        override fun onCancelled() {
            super.onCancelled()
            // Handle task cancellation, if needed
        }
    }

}
