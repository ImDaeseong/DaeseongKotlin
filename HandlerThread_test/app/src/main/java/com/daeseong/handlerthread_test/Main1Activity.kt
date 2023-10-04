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

    private lateinit var tv1: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
    private lateinit var myRunnable: MyRunnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        init()

        tv1 = findViewById(R.id.tv1)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            handler.sendEmptyMessage(1)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val message = Message.obtain()
            message.what = 2
            message.obj = "sendMessage"
            handler.sendMessage(message)
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            handler.post(myRunnable)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clear()
    }

    private fun init() {

        handlerThread = HandlerThread("handlerThread").apply { start() }

        handler = object : Handler(handlerThread.looper) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                try {
                    val sMsg = msg.obj?.toString() ?: "${msg.what} null"
                    tv1.text = sMsg
                } catch (ex: Exception) {
                    Log.e(tag, ex.message.toString())
                }
            }
        }

        myRunnable = MyRunnable()
    }

    private fun clear() {
        try {
            handler.removeCallbacks(myRunnable)
            handler.removeMessages(0)
            handlerThread.looper.quit()
            handlerThread.quit()
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    inner class MyRunnable : Runnable {
        override fun run() {
            handler.sendMessage(Message.obtain().apply {
                what = 0
                obj = "MyRunnable"
            })
        }
    }
}