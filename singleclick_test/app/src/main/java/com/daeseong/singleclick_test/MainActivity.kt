package com.daeseong.singleclick_test


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName;

    private var btn1 : Button? = null;

    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()

        btn1 = findViewById<Button>(R.id.btn1)
        btn1!!.setOnClickListener(object : OnSingleClickListener(500) {

            override fun onSingleClick(view: View) {

                isDoubleClick()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        handler!!.removeMessages(0)
    }

    fun isDoubleClick() {

        if (CheckDoublePressHandler.getInstance().isDoubleClick()) {

            Log.e(tag , "isDoubleClick");
            return
        }

        handler!!.post {
            Log.e(tag , "run");
        }
    }
}

