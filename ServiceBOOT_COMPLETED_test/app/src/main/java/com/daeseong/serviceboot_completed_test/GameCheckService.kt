package com.daeseong.serviceboot_completed_test

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import androidx.annotation.Nullable
import com.daeseong.serviceboot_completed_test.GameInfo.getGameApp
import java.util.*


class GameCheckService : Service() {

    private val tag = GameCheckService::class.java.simpleName

    private var timer: Timer? = null

    val handler = Handler(object : Handler.Callback {

        override fun handleMessage(msg: Message): Boolean {

            Log.e(tag, "handleMessage")
            LogToFile_util.getInstance().write(msg.obj.toString())

            return true
        }
    })

    private fun closeTimer() {
        try {
            if (timer != null) {
                timer!!.cancel()
                timer = null
            }
        } catch (e: Exception) {
        }
    }

    private fun startTimer() {

        closeTimer()

        timer = Timer()
        timer!!.schedule(object : TimerTask() {

            override fun run() {

                val gameItems = getGameApp(this@GameCheckService)
                for (i in gameItems.indices) {

                    Log.e(tag, "packageName:" + gameItems.get(i).packageName);

                    val msg: Message = handler.obtainMessage()
                    msg.what = 0
                    msg.obj = gameItems[i].packageName
                    handler.sendMessage(msg)
                }
            }
        }, 0, 5000)
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        LogToFile_util.getInstance().init(this)
        LogToFile_util.getInstance().write("GameCheckService start")

        Log.e(tag, "onCreate")

        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()

        LogToFile_util.getInstance().write("GameCheckService end")

        closeTimer()
        handler.removeMessages(0)

        Log.e(tag, "onDestroy")
    }
}