package com.daeseong.handlerthread_test

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName

    private var tv1: TextView? = null
    private var button1 : Button? = null
    private var button2: Button? = null

    private lateinit var thread: Thread
    private lateinit var handler: Handler
    private lateinit var myRunnable1 : MyRunnable1
    private lateinit var myRunnable2 : MyRunnable2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

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

        myRunnable1 = MyRunnable1()
        myRunnable2 = MyRunnable2()

        thread = Thread(myRunnable1)
        thread.start()
    }

    private fun clear() {

        try {

            if (handler != null) {
                handler.removeCallbacks(myRunnable2)
                handler.removeCallbacks(myRunnable1)
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

    inner class MyRunnable1 : Runnable {
        override fun run() {

            handler.post(myRunnable2);
        }
    }

    inner class MyRunnable2 : Runnable {
        override fun run() {

            val message = Message.obtain()
            message.what = 0
            message.obj = "myRunnable2"
            handler.sendMessage(message)
        }
    }
}