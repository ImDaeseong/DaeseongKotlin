package com.daeseong.http_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ImageTextView2Activity : AppCompatActivity() {

    private val tag: String = ImageTextView2Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var textView1: TextView
    private lateinit var imageView1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_text_view2)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        textView1 = findViewById(R.id.textView1)
        imageView1 = findViewById(R.id.imageView1)

        button1.setOnClickListener {
            lifecycleScope.launch {
                downloadImage()
            }
        }

        button2.setOnClickListener {
            lifecycleScope.launch {
                downloadJson()
            }
        }
    }

    private suspend fun downloadImage() {

        val url1 = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
        val progressDialog = android.app.ProgressDialog(this).apply {
            setMessage("이미지 다운로드중...")
            setCancelable(true)
            show()
        }

        val bitmap = withContext(Dispatchers.IO) {
            try {
                val url = URL(url1)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                connection.disconnect()
                bitmap
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        progressDialog.dismiss()

        setImageViewBitmap(bitmap)
    }

    private suspend fun downloadJson() {
        val url1 = "https://api.bithumb.com/public/ticker/BTC"
        val progressDialog = android.app.ProgressDialog.show(this, "접속중...", "데이터 다운로드중...", true)

        val result = withContext(Dispatchers.IO) {
            val stringBuilder = StringBuilder()
            try {
                val url = URL(url1)
                val connection = url.openConnection() as HttpURLConnection
                connection.connect()

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = BufferedInputStream(connection.inputStream)
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    reader.forEachLine { stringBuilder.append(it) }
                    reader.close()
                }
                connection.disconnect()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            stringBuilder.toString()
        }

        progressDialog.dismiss()

        loadJsonData(result)
    }

    fun setImageViewBitmap(bitmap: Bitmap?) {
        val defaultBitmap = BitmapFactory.decodeResource(resources, R.drawable.r)
        imageView1.setImageBitmap(bitmap ?: defaultBitmap)
    }

    fun loadJsonData(data: String?) {
        textView1.text = data
    }

}
