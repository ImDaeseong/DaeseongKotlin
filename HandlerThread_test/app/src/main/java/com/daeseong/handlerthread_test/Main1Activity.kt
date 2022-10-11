package com.daeseong.handlerthread_test

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var tv1: TextView? = null
    private var button1 : Button? = null
    private var button2: Button? = null
    private var button3: Button? = null

    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
    private lateinit var myRunnable: MyRunnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        init()

        tv1 = findViewById(R.id.tv1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {

            if (handlerThread != null) {
                handler.sendEmptyMessage(1)
            }
        }

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener {

            if (handlerThread != null) {
                val message = Message.obtain()
                message.what = 2
                message.obj = "sendMessage"
                handler.sendMessage(message)
            }
        }

        button3 = findViewById(R.id.button3)
        button3!!.setOnClickListener {

            if (handlerThread != null) {
                handler.post(myRunnable)
                //handler.postDelayed(myRunnable, 1000)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        clear()
    }

    private fun init() {

        handlerThread = HandlerThread("handlerThread")
        handlerThread.start()

        handler = object : Handler(handlerThread.looper) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                try {

                    var sMsg: String = if(msg.obj == null){
                        String.format("%d null", msg.what)
                    } else{
                        String.format("%d %s", msg.what, msg.obj.toString()!!)
                    }
                    tv1!!.text = sMsg

                } catch (ex: Exception) {
                    Log.e(tag, ex.message.toString())
                } finally {
                }
            }
        }

        myRunnable = MyRunnable()
    }

    private fun clear() {

        try {

            if (handler != null) {
                handler.removeCallbacks(myRunnable)
            }

            if (handler != null) {
                handler.removeMessages(0)
            }

            if (handlerThread != null) {
                handlerThread.looper.quit()
                handlerThread.quit()
            }
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        } finally {
        }
    }

    inner class MyRunnable : Runnable {
        override fun run() {
            if (handlerThread != null) {
                val message = Message.obtain()
                message.what = 0
                message.obj = "MyRunnable"
                handler.sendMessage(message)
            }
        }
    }
}