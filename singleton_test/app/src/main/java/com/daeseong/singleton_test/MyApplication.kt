package com.im.daeseong.singleton_test

import android.app.Application
import android.content.Context
import android.os.Build
import com.im.daeseong.singleton_test.util.Screenutil
import com.im.daeseong.singleton_test.util.SharedPreferencesUtil

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

        SharedPreferencesUtil.getInstance().init(this)

        DbHandler.getInstance().init(this)

        Screenutil.getInstance().init(this)
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
