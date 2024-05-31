package com.daeseong.loading_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.daeseong.loading_test.Controls.LoadingDialog1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class Loading1Activity : AppCompatActivity() {

    private val tag: String = Loading1Activity::class.java.simpleName

    private lateinit var loadingDialog: LoadingDialog1

    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading1)

        loadingDialog = LoadingDialog1(this)

        startTimer()

        showLoading()
    }

    override fun onDestroy() {
        super.onDestroy()

        hideLoading()
    }

    private fun showLoading() {

        if(!loadingDialog.isShowing()){
            loadingDialog.show()
        }
    }

    private fun hideLoading() {

        lifecycleScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss()
                }
            }
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