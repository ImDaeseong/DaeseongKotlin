package com.daeseong.threadtask_test

import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.threadtask_test.Util.DownloadManager

class Main5Activity : AppCompatActivity() {

    private val tag = Main5Activity::class.java.simpleName

    private var iv1: ImageView? = null
    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        iv1 = findViewById(R.id.iv1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            if (!isNetworkAvailable()) {
                return@OnClickListener
            }

            val surl = "https://"
            DownloadManager.getInstance().getImage(surl, object : DownloadManager.onCompleteListen {
                override fun onCompleted(bitmap: Bitmap?) {
                    //Log.e(tag, "onCompleted:$bitmap")
                    iv1!!.setImageBitmap(bitmap)
                }

                override fun onError(sMessage: String?) {
                    Log.e(tag, "onError:$sMessage")
                }
            })

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