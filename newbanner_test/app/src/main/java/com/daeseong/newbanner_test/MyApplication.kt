package com.daeseong.newbanner_test

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        private lateinit var mContext: Context
        lateinit var instance: MyApplication

        fun getContext(): Context = mContext.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
        instance = this
    }
}
