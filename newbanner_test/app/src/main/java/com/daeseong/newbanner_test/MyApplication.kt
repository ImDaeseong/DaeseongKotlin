package com.daeseong.newbanner_test

import android.app.Application
import android.content.Context


class MyApplication : Application() {

    private val tag: String = MyApplication::class.java.simpleName

    companion object {

        private lateinit var mContext: Context
        private lateinit var instance: MyApplication

        fun getContext(): Context {
            return mContext.applicationContext
        }

        fun getInstance(): MyApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        mContext = this
        instance = this
    }
}