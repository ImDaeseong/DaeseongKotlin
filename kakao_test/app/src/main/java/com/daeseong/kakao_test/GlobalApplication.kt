package com.daeseong.kakao_test

import android.app.Application
import android.content.Context
import com.kakao.auth.KakaoSDK

class GlobalApplication : Application() {

    companion object {
        private lateinit var mContext: Context
        private lateinit var mInstance: GlobalApplication

        fun getContext(): Context {
            return mContext.applicationContext
        }

        fun getInstance(): GlobalApplication {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()

        mContext = this
        mInstance = this

        // Kakao Sdk 초기화
        KakaoSDK.init(KakaoSDKAdapter())
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}