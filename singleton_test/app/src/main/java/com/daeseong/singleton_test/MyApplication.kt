package com.daeseong.singleton_test

import android.app.Application
import android.content.Context
import android.os.Build
import com.daeseong.singleton_test.Db.DbHandler
import com.daeseong.singleton_test.util.Screenutil
import com.daeseong.singleton_test.util.SharedPreferencesutil


class MyApplication : Application() {

    private val tag: String = MyApplication::class.java.simpleName

    companion object {
        private lateinit var mContext: Context
        private lateinit var mInstance: MyApplication

        fun getContext(): Context {
            return mContext.applicationContext
        }

        fun getInstance(): MyApplication {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()

        mContext = this
        mInstance = this

        Screenutil.getInstance().init(this);
        SharedPreferencesutil.getInstance().init(this);
        DbHandler.getInstance().init(this)
    }

    fun getVersionSDK(): Int {
        return Build.VERSION.SDK_INT
    }

    fun getVersionRelease(): String {
        return Build.VERSION.RELEASE
    }

    fun getDevice(): String {
        return Build.DEVICE
    }
}