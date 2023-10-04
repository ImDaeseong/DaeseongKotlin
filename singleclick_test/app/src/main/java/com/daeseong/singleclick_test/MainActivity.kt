package com.daeseong.singleclick_test

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName
    private var btn1: Button? = null
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)
        btn1?.setOnClickListener(object : OnSingleClickListener(500) {
            override fun onSingleClick(view: android.view.View) {
                handleSingleClick()
            }
        })
    }

    private fun handleSingleClick() {
        if (CheckDoublePressHandler.getInstance().isDoubleClick()) {
            Log.e(tag, "isDoubleClick")
            return
        }

        handler.post {
            Log.e(tag, "run")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
