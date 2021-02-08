package com.daeseong.okhttpclient_test

import android.app.Application
import android.content.res.Configuration

class OKHttpClientApplication : Application() {

    private val tag = OKHttpClientApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}