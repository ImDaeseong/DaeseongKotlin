package com.daeseong.threadtask_test

import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.threadtask_test.Util.DownloadText

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var handler: Handler? = null

    private var tv1: TextView? = null
    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        handler = Handler(Looper.getMainLooper())

        tv1 = findViewById(R.id.tv1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            if (!isNetworkAvailable()) {
                return@OnClickListener
            }

            val surl = "https://"
            val downloadText = DownloadText(surl, handler!!)
            downloadText.setonDownloadTextListen(object : DownloadText.onDownloadTextListen {

                override fun onCompleted(downloadText: DownloadText?) {
                    //Log.e(tag, "onCompleted:" + downloadText!!.getText())
                    tv1!!.text = downloadText!!.getText()
                }

                override fun onError(downloadText: DownloadText?, sMessage: String?) {
                    Log.e(tag, "onError:$sMessage")
                }
            })
            downloadText.start()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        handler!!.removeMessages(0)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}