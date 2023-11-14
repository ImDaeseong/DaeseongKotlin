package com.daeseong.loading_test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var timer: Timer? = null

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    private lateinit var loadingDialog: LoadingDialog
    private lateinit var loadingDialog1: LoadingDialog1
    private lateinit var loadingOverlay: LoadingOverlay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingDialog = LoadingDialog(this)
        loadingDialog1 = LoadingDialog1(this)

        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            startTimer()
            showLoading()
        }

        btn2 = findViewById(R.id.btn2)
        btn2.setOnClickListener {
            if (!loadingDialog.isShowing) {
                showLoading1()
            } else {
                hideLoading1()
            }
        }

        btn3 = findViewById(R.id.btn3)
        btn3.setOnClickListener {
            showLoading2()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        closeTimer()
    }

    private fun showLoading1() {
        loadingDialog.show()
    }

    private fun hideLoading1() {
        try {
            loadingDialog.dismiss()
        } catch (e: Exception) {
        }
    }

    private fun showLoading2() {
        loadingDialog1.show()
    }

    private fun hideLoading2() {
        try {
            loadingDialog1.dismiss()
        } catch (e: Exception) {
        }
    }

    private fun showLoading() {
        if (!::loadingOverlay.isInitialized) {
            loadingOverlay = LoadingOverlay(this)
        }
        loadingOverlay.show()
    }

    private fun hideLoading() {
        if (::loadingOverlay.isInitialized && loadingOverlay.isShowing) {
            loadingOverlay.dismiss()
        }
    }

    private fun closeTimer() {
        try {
            timer?.cancel()
            timer = null
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
                } catch (e: Exception) {
                }
            }
        }, 0, 1000)
    }
}
