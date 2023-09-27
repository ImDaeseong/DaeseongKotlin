package com.daeseong.http_test

import DownloadText
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TextView1Activity : AppCompatActivity() {

    private lateinit var downloadText: DownloadText
    private lateinit var textView1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view1)

        textView1 = findViewById(R.id.textView1)

        if (isConnection()) {
            val url1 = "https://api.bithumb.com/public/ticker/BTC"
            downloadText = DownloadText(textView1)
            downloadText.execute(url1)
        } else {
            textView1.text = "not connect"
        }
    }

    private fun isConnection(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true && networkInfo.isAvailable
    }
}
