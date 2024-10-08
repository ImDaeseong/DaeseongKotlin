package com.daeseong.http_test

import DownloadJson
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TextView2Activity : AppCompatActivity() {

    private lateinit var downloadJson: DownloadJson
    private lateinit var textView1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view2)

        textView1 = findViewById(R.id.textView1)

        if (isConnected()) {
            val url1 = "https://api.bithumb.com/public/ticker/BTC"
            downloadJson = DownloadJson(textView1)
            downloadJson.execute(url1)
        } else {
            textView1.text = "Not connected"
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected == true
    }
}
