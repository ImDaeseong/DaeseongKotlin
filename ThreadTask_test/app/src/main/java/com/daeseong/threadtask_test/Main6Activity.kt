package com.daeseong.threadtask_test

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.threadtask_test.Util.DownloadTextTask

class Main6Activity : AppCompatActivity() {

    private val tag = Main6Activity::class.java.simpleName

    private var downloadTextTask: DownloadTextTask? = null

    private var tv1: TextView? = null
    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        tv1 = findViewById(R.id.tv1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            if (!isNetworkAvailable()) {
                return@OnClickListener
            }

            val surl = "https://"
            downloadTextTask = DownloadTextTask(tv1!!)
            downloadTextTask!!.execute(surl)
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