package com.daeseong.volley_test

import android.app.Application
import android.content.res.Configuration
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MyApplication : Application() {

    companion object {

        private lateinit var requestQueue: RequestQueue
        private lateinit var instance: MyApplication

        fun getInstance(): MyApplication {
            return instance
        }

        fun getRequestQueue(): RequestQueue {
            return requestQueue
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        requestQueue = Volley.newRequestQueue(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}