package com.daeseong.loading_test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var timer: Timer? = null

    private var btn1 : Button? = null;
    private var btn2 : Button? = null;
    private var btn3 : Button? = null;

    private var loadingDialog: LoadingDialog? = null
    private var loadingDialog1: LoadingDialog1? = null
    private var loadingOverlay: LoadingOverlay? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingDialog = LoadingDialog(this)
        loadingDialog1 = LoadingDialog1(this)

        btn1 = findViewById<Button>(R.id.btn1)
        btn1!!.setOnClickListener {

            startTimer()
            showLoading()
        }

        btn2 = findViewById<Button>(R.id.btn2)
        btn2!!.setOnClickListener {

            if(!loadingDialog!!.isShowing){
                showLoading1()
            } else {
                hideLoading1()
            }
        }

        btn3 = findViewById<Button>(R.id.btn3)
        btn3!!.setOnClickListener {

            showLoading2()
        }
    }

    fun showLoading1() {
        loadingDialog!!.show()
    }

    fun hideLoading1() {
        try {
            loadingDialog!!.dismiss()
        } catch (e: java.lang.Exception) {
        }
    }

    fun showLoading2() {
        loadingDialog1!!.show()
    }

    fun hideLoading2() {
        try {
            loadingDialog1!!.dismiss()
        } catch (e: java.lang.Exception) {
        }
    }

    fun showLoading() {

        if (loadingOverlay == null) {
            loadingOverlay = LoadingOverlay(this)
        }
        loadingOverlay!!.show()
    }

    fun hideLoading() {

        if (loadingOverlay != null && loadingOverlay!!.isShowing) {
            loadingOverlay!!.dismiss()
        }
    }

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
            var nTimeCount = 0
            override fun run() {
                try {
                    nTimeCount++
                    if (nTimeCount > 5) {
                        closeTimer()
                        runOnUiThread {

                            hideLoading()
                        }
                    }
                } catch (e: java.lang.Exception) {
                }
            }
        }, 0, 1000)
    }
}
