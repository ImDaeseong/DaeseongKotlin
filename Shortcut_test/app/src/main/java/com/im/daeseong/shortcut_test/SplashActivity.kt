package com.im.daeseong.shortcut_test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        private val tag = SplashActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sPackageName = intent.getStringExtra("pkg")
        val sID = intent.getStringExtra("userId")

        sPackageName?.let { pkg ->
            if (pkg.isNotEmpty()) {
                Log.e(tag, "sPackageName:$pkg")
                Toast.makeText(this, pkg, Toast.LENGTH_LONG).show()
            }
        }

        sID?.let { id ->
            if (id.isNotEmpty()) {
                Log.e(tag, "sID:$id")
                Toast.makeText(this, id, Toast.LENGTH_LONG).show()
            }
        }

        init()
    }

    private fun init() {

        // 상태바 숨기기
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // MainActivity 이동
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}