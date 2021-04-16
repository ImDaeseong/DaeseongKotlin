package com.daeseong.threadtask_test

import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.threadtask_test.Util.DownloadImageRunnable

class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName

    private var handler: Handler? = null

    private var iv1: ImageView? = null
    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                if (msg.what == 1) {

                    //Log.e(tag, "onCompleted:" + msg.obj as Bitmap)
                    iv1!!.setImageBitmap(msg.obj as Bitmap)
                } else if (msg.what == 2) {

                    Log.e(tag, "onError:" + msg.obj as String)
                }
            }
        }

        iv1 = findViewById(R.id.iv1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            if (!isNetworkAvailable()) {
                return@OnClickListener
            }

            val surl ="https://"
            Thread(DownloadImageRunnable(surl, handler!!)).start()
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