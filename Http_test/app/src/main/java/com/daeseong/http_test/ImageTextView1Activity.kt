package com.daeseong.http_test

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit

class ImageTextView1Activity : AppCompatActivity() {

    private val tag: String = ImageTextView1Activity::class.java.simpleName

    private lateinit var textView1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_text_view1)

        textView1 = findViewById(R.id.textView1)

        loadData()
    }

    private fun loadData() {

        lifecycleScope.launch(Dispatchers.IO) {

            val url1 = "https://api.bithumb.com/public/ticker/BTC"
            val result = getUrlData(url1)
            withContext(Dispatchers.Main) {
                textView1.text = result
            }
        }
    }

    private suspend fun getUrlData(sUrl: String): String = withContext(Dispatchers.IO) {

        try {

            val url = URL(sUrl)
            val connection = (url.openConnection() as HttpURLConnection).apply {
                connectTimeout = TimeUnit.SECONDS.toMillis(10).toInt()
                useCaches = false
            }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                connection.inputStream.use { inputStream ->
                    InputStreamReader(inputStream).use { isr ->
                        BufferedReader(isr).use { br ->
                            buildString {
                                var line: String?
                                while (br.readLine().also { line = it } != null) {
                                    append(line).append("\n")
                                }
                            }
                        }
                    }
                }
            } else {
                "Error: ${connection.responseCode}"
            }.also {
                connection.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error: ${e.message}"
        }
    }

}
