package com.daeseong.threadtask_test

import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.threadtask_test.Util.DownloadImage

class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private var handler: Handler? = null

    private var iv1: ImageView? = null
    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        handler = Handler(Looper.getMainLooper())

        iv1 = findViewById(R.id.iv1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            if (!isNetworkAvailable()) {
                return@OnClickListener
            }

            val surl = "https://"
            val downloadImage = DownloadImage(surl, handler!!)
            downloadImage.setonDownloadImageListen(object : DownloadImage.onDownloadImageListen {
                override fun onCompleted(downloadImage: DownloadImage?) {
                    //Log.e(tag, "onCompleted:" + downloadImage!!.getBitmap())
                    iv1!!.setImageBitmap(downloadImage!!.getBitmap())
                }

                override fun onError(downloadImage: DownloadImage?, sMessage: String?) {
                    Log.e(tag, "onError:$sMessage")
                }
            })
            downloadImage.start()
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