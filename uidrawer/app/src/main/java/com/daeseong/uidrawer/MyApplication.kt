package com.daeseong.uidrawer

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    private val tag: String = MyApplication::class.java.simpleName

    companion object {

        private lateinit var mContext: Context
        private lateinit var instance: MyApplication

        private var nScreenWidth = 0
        private var nScreenHeight = 0

        fun getContext(): Context {
            return mContext.applicationContext
        }

        fun getInstance(): MyApplication {
            return instance
        }

        fun getScreenWidth(): Int {
            return nScreenWidth
        }

        fun getScreenHeight(): Int {
            return nScreenHeight
        }
    }

    override fun onCreate() {
        super.onCreate()

        nScreenWidth = resources.displayMetrics.widthPixels
        nScreenHeight = resources.displayMetrics.heightPixels

        instance = this
        mContext = applicationContext
    }
}