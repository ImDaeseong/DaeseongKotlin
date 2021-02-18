package com.daeseong.viewpager_test

import android.app.Application

class MyApplication : Application() {

    private val tag: String = MyApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
    }
}