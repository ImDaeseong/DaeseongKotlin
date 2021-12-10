package com.daeseong.swiperefreshlayout_test

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity4 : AppCompatActivity() {

    private val TAG = MainActivity4::class.java.simpleName

    private var swipeRefreshLayoutEx: SwipeRefreshLayoutEx? = null

    private var waitConstraintLayout: ConstraintLayoutEx? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        waitConstraintLayout = findViewById<View>(R.id.wait) as ConstraintLayoutEx

        swipeRefreshLayoutEx = findViewById<View>(R.id.swLayout) as SwipeRefreshLayoutEx
        swipeRefreshLayoutEx!!.hideProgressBar(true)
        swipeRefreshLayoutEx!!.setOnRefreshListener {

            swipeRefreshLayoutEx!!.isRefreshing = true

            waitConstraintLayout!!.show()

            Handler().postDelayed({

                if (swipeRefreshLayoutEx!!.isRefreshing) {
                    swipeRefreshLayoutEx!!.isRefreshing = false
                }

                requestData()

                waitConstraintLayout!!.hide()

            }, 1000)
        }
    }

    fun requestData() {
        Log.e(TAG, "업데이트 완료")
    }
}