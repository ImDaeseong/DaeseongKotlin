package com.daeseong.http_test


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.http_test.HttpUtil.DownloadJson


class TextView2Activity : AppCompatActivity() {

    private var downloadJson: DownloadJson? = null
    var textView1: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view2)

        textView1 = findViewById<TextView>(R.id.textView1)

        try {

            if (IsConnection()) {
                val url1 = "https://"
                downloadJson = DownloadJson(textView1!!)
                downloadJson!!.execute(url1)
            } else {
                textView1!!.text = "not connect"
            }

        } catch (e: Exception) {

            downloadJson!!.cancel(true)
            textView1!!.text = "Error"
            e.printStackTrace()
        }
    }

    fun IsConnection(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo ?: return false
        if (!networkInfo.isConnected) {
            return false
        }
        return networkInfo.isAvailable
    }
}
