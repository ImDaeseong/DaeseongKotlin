package com.daeseong.handlerthread_test

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private var tv1: TextView? = null
    private var button1 : Button? = null
    private var button2: Button? = null

    private lateinit var thread: Thread
    private lateinit var handler: Handler
    private lateinit var myRunnable: MyRunnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        init()

        tv1 = findViewById(R.id.tv1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {

            if (handler != null) {
                handler.sendEmptyMessage(1)
            }
        }

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener {

            if (handler != null) {
                val message = Message.obtain()
                message.what = 2
                message.obj = "sendMessage"
                handler.sendMessage(message)
            }
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

        thread = Thread(myRunnable)
        thread.start();
    }

    private fun clear() {

        try {

            if (handler != null) {
                handler.removeCallbacks(myRunnable)
            }

            if (handler != null) {
                handler.removeMessages(0)
            }

            if (thread != null) {
                thread.interrupt()
            }
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        } finally {
        }
    }

    inner class MyRunnable : Runnable {
        override fun run() {

            val message = Message.obtain()
            message.what = 0
            message.obj = "MyRunnable"
            handler.sendMessage(message)
        }
    }
}