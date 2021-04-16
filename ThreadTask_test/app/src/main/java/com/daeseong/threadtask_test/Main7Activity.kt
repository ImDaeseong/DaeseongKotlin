package com.daeseong.threadtask_test

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.threadtask_test.Util.DownloadImageTask

class Main7Activity : AppCompatActivity() {

    private val tag = Main7Activity::class.java.simpleName

    private var downloadImageTask: DownloadImageTask? = null

    private var iv1: ImageView? = null
    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        iv1 = findViewById(R.id.iv1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            if (!isNetworkAvailable()) {
                return@OnClickListener
            }

            val surl = "https://"
            downloadImageTask = DownloadImageTask(iv1!!)
            downloadImageTask!!.execute(surl)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}