package com.daeseong.splash_test

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.splash_test.util.SharedPreferences_util
import java.util.*


class Splash1Activity : AppCompatActivity() {

    private val tag: String = Splash1Activity::class.java.simpleName

    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash1)


        //first
        timer = Timer(true)
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                val isFirst = SharedPreferences_util().getValue(this@Splash1Activity, "FRIST", false) as Boolean
                if (isFirst) {
                    Log.e(tag, "Second")
                } else {
                    Log.e(tag, "First")
                    SharedPreferences_util().setValue(this@Splash1Activity, "FRIST", true)
                }
                finish()
            }
        }
        timer!!.schedule(task, 1000)


        //second
        /*
        Handler().postDelayed(Runnable {
            val isFirst = SharedPreferences_util().getValue(this@Splash1Activity, "FRIST", false) as Boolean
            if (isFirst) {
                Log.e(tag, "Second")
            } else {
                Log.e(tag, "First")
                SharedPreferences_util().setValue(this@Splash1Activity, "FRIST", true)
            }
            finish()
        }, 1000)
        */

    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            if (timer != null) {
                timer!!.cancel()
                timer = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
