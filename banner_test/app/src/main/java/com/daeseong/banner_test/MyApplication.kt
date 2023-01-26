package com.daeseong.banner_test

import android.app.Application

class MyApplication : Application() {

    private val tag: String = MyApplication::class.java.simpleName

    companion object {

        private lateinit var mInstance: MyApplication

        fun getInstance(): MyApplication {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()

        mInstance = this
        Banner_util.getInstance().setDownloadBanner()
    }

}