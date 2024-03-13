package com.daeseong.paging_test

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.paging_test.Common.GetStringTask
import com.daeseong.paging_test.Common.HttpUtil.GetDataBitmap

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private lateinit var tv1: TextView
    private lateinit var button1: Button
    private lateinit var iv1: ImageView
    private lateinit var sResult: String

    private lateinit var sUrl: String
    private val sSearchkey = "android"

    private var thread: HandlerThread? = null
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        downloadImg(ConstantsUrl.sImgUrl)

        initThread()

        iv1 = findViewById<View>(R.id.iv1) as ImageView
        tv1 = findViewById<View>(R.id.tv1) as TextView
        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener(View.OnClickListener {

            tv1!!.text = ""
            handler!!.sendEmptyMessage(1)
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        StopThread()
    }

    private fun initThread() {

        try {

            thread = HandlerThread("paging")
            thread!!.start()
            handler = object : Handler(thread!!.looper) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    when (msg.what) {
                        1 -> getData()
                        else -> {}
                    }
                }
            }
        } catch (ex: java.lang.Exception) {
            ex.message.toString()
        }
    }

    private fun StopThread() {

        try {

            if (thread != null) {
                thread!!.looper.quit()
                thread!!.quit()
            }
        } catch (ex: java.lang.Exception) {
            ex.message.toString()
        } finally {
            handler = null
            thread = null
        }
    }

    private fun downloadImg(sUrl: String) {

        Thread {

            try {

                val bitmap = GetDataBitmap(sUrl)
                runOnUiThread {
                    iv1.setImageBitmap(bitmap)
                }
            } catch (ex: java.lang.Exception) {
                Log.e(tag, ex.message.toString())
            }

        }.start()
    }

    private fun getData() {

        try {

            sUrl = String.format("%s&q=%s", ConstantsUrl.sUrl1, sSearchkey)
            sResult = GetStringTask().execute(sUrl)!!

            runOnUiThread {
                tv1.text = sResult
            }

        } catch (ex: java.lang.Exception) {
            Log.e(tag, ex.message.toString())
        }
    }
}