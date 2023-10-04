package com.daeseong.handlerthread_test

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main4Activity : AppCompatActivity() {

    private val tag = Main4Activity::class.java.simpleName

    private lateinit var tv1: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button

    private lateinit var myThread: MyThread
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        init()

        tv1 = findViewById(R.id.tv1)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            handler.sendEmptyMessage(1)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val message = Message.obtain().apply {
                what = 2
                obj = "sendMessage"
            }
            handler.sendMessage(message)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clear()
    }

    private fun init() {

        handler = object : Handler(mainLooper) {
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

        myThread = MyThread(handler, true, "sParam")
        myThread.start()
    }

    private fun clear() {
        try {
            handler.removeMessages(0)
            myThread.clear()
            myThread.interrupt()
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    private class MyThread(private val handler: Handler?, private var bRun: Boolean, private val sParam: String) :
        Thread() {

        override fun run() {
            super.run()
            while (bRun) {
                try {
                    handler?.sendEmptyMessage(0)

                    // 1초에 한 번씩 전달
                    sleep(1000)
                } catch (ex: Exception) {
                    Log.e("TAG", "MyThread run")
                }
            }
        }

        fun sendMessage() {
            val message = Message.obtain().apply {
                what = 2
                obj = "sendMessage"
            }
            handler?.sendMessage(message)
        }

        fun clear() {
            bRun = false
        }
    }

}