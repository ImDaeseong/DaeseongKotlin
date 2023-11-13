package com.daeseong.swiperefreshlayout_test

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity4 : AppCompatActivity() {

    private val tag = MainActivity4::class.java.simpleName

    private lateinit var swipeRefreshLayoutEx: SwipeRefreshLayoutEx
    private lateinit var waitConstraintLayout: ConstraintLayoutEx

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        waitConstraintLayout = findViewById(R.id.wait)
        swipeRefreshLayoutEx = findViewById(R.id.swLayout)

        swipeRefreshLayoutEx.hideProgressBar(true)
        swipeRefreshLayoutEx.setOnRefreshListener {

            swipeRefreshLayoutEx.isRefreshing = true

            waitConstraintLayout.show()

            Handler(Looper.getMainLooper()).postDelayed({

                if (swipeRefreshLayoutEx.isRefreshing) {
                    swipeRefreshLayoutEx.isRefreshing = false
                }

                requestData()

                waitConstraintLayout.hide()

            }, 1000)
        }
    }

    private fun requestData() {
        Log.e(tag, "업데이트 완료")
    }
}
