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

    private var tv1: TextView? = null
    private var button1 : Button? = null
    private var button2: Button? = null

    private lateinit var myThread: MyThread
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

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

        myThread = MyThread(handler, true, "sParam")
        myThread.start()
    }

    private fun clear() {

        try {

            if (handler != null) {
                handler.removeMessages(0)
            }

            if (myThread != null) {
                myThread.clear()
                myThread.interrupt()
            }
        } catch (ex: java.lang.Exception) {
            Log.e(tag, ex.message.toString())
        } finally {
        }
    }

    private class MyThread(handler: Handler?, bRun: Boolean, sParam: String) : Thread() {

        private var handler: Handler? = null
        private val sParam: String
        private var bRun = false

        init {
            this.handler = handler
            this.bRun = bRun
            this.sParam = sParam
        }

        override fun run() {
            super.run()
            while (bRun) {

                //Log.e("TAG", "MyThread run")

                try {
                    handler?.sendEmptyMessage(0)

                    //1초에 한번씩 전달
                    sleep(1000)
                } catch (ex: java.lang.Exception) {
                }
            }
        }

        fun sendMessage() {
            val message = Message.obtain()
            message.what = 2
            message.obj = "sendMessage"
            handler!!.sendMessage(message)
        }

        fun clear() {
            bRun = false
        }
    }
}