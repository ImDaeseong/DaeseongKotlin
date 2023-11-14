package com.daeseong.gameinfo_test

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import java.util.*

class GameCheckService : Service() {

    private val tag: String = GameCheckService::class.java.simpleName

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            Log.e(tag, "mainMsg:${msg.obj.toString()}")
            super.handleMessage(msg)
        }
    }

    private var timer: Timer? = null

    private fun closeTimer() {
        try {
            timer?.cancel()
            timer = null
        } catch (e: Exception) {
            Log.e(tag, "mainMsg:${e.message.toString()}")
        }
    }

    private fun startTimer() {
        closeTimer()
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {

                val gameItems: List<GameInfo.GameItem> = GameInfo().getGameApp(applicationContext)
                for (i in gameItems.indices) {
                    val msg: Message = handler.obtainMessage()
                    msg.what = 0
                    msg.obj = gameItems[i].packageName
                    handler.sendMessage(msg)
                }
            }
        }, 0, 1000)
    }

    override fun onCreate() {
        super.onCreate()
        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        closeTimer()
        handler.removeMessages(0)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
