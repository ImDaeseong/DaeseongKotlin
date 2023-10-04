package com.daeseong.splash_test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Splash2Activity : AppCompatActivity() {

    private val tag: String = Splash2Activity::class.java.simpleName

    private var handler: Handler = Handler(Looper.getMainLooper())
    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

        //액티비티 메인 화면 클릭시 인식
        rootView = findViewById(R.id.content)
    }

    private val dismissSplashRunnable: Runnable = Runnable {
        // Log.e(tag, "run")
        dismissSplah()
    }

    private fun dismissSplah() {

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onResume() {
        super.onResume()

        //Log.e(tag, "onResume")
        handler.postDelayed(dismissSplashRunnable, 5000)
    }

    override fun onPause() {
        super.onPause()

        //Log.e(tag, "onPause")
        handler.removeCallbacks(dismissSplashRunnable)
    }
}